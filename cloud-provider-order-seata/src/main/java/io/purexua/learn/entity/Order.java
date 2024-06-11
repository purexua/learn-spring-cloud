package io.purexua.learn.entity;

import lombok.Data;

@Data
public class Order {
  private Long id;
  private Long userId;
  private Long productId;
  private Integer count;
  private Long money;
  private Integer status;
}
