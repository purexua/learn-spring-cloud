package io.purexua.learn.mapper;

import io.purexua.learn.entity.Pay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PayMapper {
  int addPay(Pay pay);

  int deletePay(Integer id);

  int updatePay(Pay pay);

  Pay getPayById(Integer id);

  List<Pay> getAllPay();

}