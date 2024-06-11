package io.purexua.learn.controller;

import io.purexua.learn.api.PayFeignSentinelApi;
import io.purexua.learn.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderNacosController {
  @Resource
  private RestTemplate restTemplate;

  @Value("${service-url.nacos-user-service}")
  private String serverURL;
  @Qualifier("io.purexua.learn.api.PayFeignSentinelApi")

  @Resource
  private PayFeignSentinelApi payFeignSentinelApi;

  @GetMapping("/consumer/pay/nacos/{id}")
  public String paymentInfo(@PathVariable("id") Integer id) {
    String result = restTemplate.getForObject(serverURL + "/pay/nacos/" + id, String.class);
    return result + "\t" + "    我是OrderNacosController8102调用者。。。。。。";
  }

  @GetMapping(value = "/consumer/pay/nacos/get/{orderNo}")
  public ResultData<String> getPayByOrderNo(@PathVariable("orderNo") String orderNo) {
    return payFeignSentinelApi.getPayByOrderNo(orderNo);
  }
}
