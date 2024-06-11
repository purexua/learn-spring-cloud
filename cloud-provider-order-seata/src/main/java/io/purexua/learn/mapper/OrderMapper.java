package io.purexua.learn.mapper;

import io.purexua.learn.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
  int insert(Order order);

  int update(Order order);

  Order selectById(Long id);
}
