package io.purexua.learn.service;

import io.purexua.learn.entity.Pay;

import java.util.List;

public interface PayService {
  int add(Pay pay);

  int delete(Integer id);

  int update(Pay pay);

  Pay get(Integer id);

  List<Pay> getAllPay();
}
