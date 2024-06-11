package io.purexua.learn.service.impl;

import io.purexua.learn.api.AccountFeignApi;
import io.purexua.learn.api.StorageFeignApi;
import io.purexua.learn.entity.Order;
import io.purexua.learn.mapper.OrderMapper;
import io.purexua.learn.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
  @Resource
  private OrderMapper orderMapper;

  @Resource
  private StorageFeignApi storageFeignApi;
  @Resource
  private AccountFeignApi accountFeignApi;

  @Override
  @GlobalTransactional(name = "purexua-create-order", rollbackFor = Exception.class) //AT
  //@GlobalTransactional @Transactional(rollbackFor = Exception.class) //XA
  public void create(Order order) {
    String xid = RootContext.getXID();
    order.setStatus(0);
    int result = orderMapper.insert(order);
    Order orderFromDB = null;
    if (result > 0) {
      orderFromDB = orderMapper.selectById(order.getId());
      storageFeignApi.decrease(orderFromDB.getProductId(), orderFromDB.getCount());
      accountFeignApi.decrease(orderFromDB.getUserId(), orderFromDB.getMoney());
      orderFromDB.setStatus(1);
orderMapper.update(orderFromDB);
    }
    log.info("==================>结束新建订单\txid_order:{}", xid);

  }
}
