package io.purexua.learn.controller;

import io.purexua.learn.entity.Order;
import io.purexua.learn.resp.ResultData;
import io.purexua.learn.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

  @Resource
  private OrderService orderService;

  @GetMapping("/order/create")
  public ResultData<String> create(Order order) {
    orderService.create(order);
    return ResultData.success("订单创建成功");
  }
}
