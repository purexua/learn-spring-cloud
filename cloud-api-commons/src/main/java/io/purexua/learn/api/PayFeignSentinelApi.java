package io.purexua.learn.api;

import io.purexua.learn.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "cloud-provider-payment-alibaba", fallback = PayFeignSentinelApiFallBack.class)
public interface PayFeignSentinelApi {
  @GetMapping("/pay/nacos/get/{orderNo}")
  ResultData<String> getPayByOrderNo(@PathVariable("orderNo") String orderNo);
}