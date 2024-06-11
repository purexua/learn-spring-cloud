package io.purexua.learn.controller;

import io.purexua.learn.resp.ResultData;
import io.purexua.learn.service.StorageService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {
  @Resource
  private StorageService storageService;

  @PostMapping("/storage/decrease")
  public ResultData<String> decrease(Long productId, Integer count) {
    storageService.decrease(productId, count);
    return ResultData.success("扣减库存成功!");
  }
}
