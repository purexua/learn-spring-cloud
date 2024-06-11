package io.purexua.learn.controller;

import io.purexua.learn.api.PayFeignApi;
import io.purexua.learn.entity.PayDTO;
import io.purexua.learn.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderGateWayController {
  @Resource
  private PayFeignApi payFeignApi;

  @GetMapping(value = "/feign/pay/gateway/get/{id}")
  public ResultData<PayDTO> getById(@PathVariable("id") Integer id) {
    return payFeignApi.getById(id);
  }

  @GetMapping(value = "/feign/pay/gateway/info")
  public ResultData<String> getGatewayInfo() {
    return payFeignApi.getGatewayInfo();
  }
}
