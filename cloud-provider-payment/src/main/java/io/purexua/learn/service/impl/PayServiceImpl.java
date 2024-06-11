package io.purexua.learn.service.impl;

import io.purexua.learn.entity.Pay;
import io.purexua.learn.mapper.PayMapper;
import io.purexua.learn.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayServiceImpl implements PayService {

  private final PayMapper payMapper;

  @Autowired
  public PayServiceImpl(PayMapper payMapper) {
    this.payMapper = payMapper;
  }

  @Override
  public int add(Pay pay) {
    return payMapper.addPay(pay);
  }

  @Override
  public int delete(Integer id) {
    return payMapper.deletePay(id);
  }

  @Override
  public int update(Pay pay) {
    return payMapper.updatePay(pay);
  }

  @Override
  public Pay get(Integer id) {
    return payMapper.getPayById(id);
  }

  @Override
  public List<Pay> getAllPay() {
    return payMapper.getAllPay();
  }
}
