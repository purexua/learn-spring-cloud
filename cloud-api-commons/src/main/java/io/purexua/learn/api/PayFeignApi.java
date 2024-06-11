package io.purexua.learn.api;

import io.purexua.learn.entity.PayDTO;
import io.purexua.learn.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@FeignClient(value = "cloud-provider-payment") //不使用网关
@FeignClient(value = "cloud-provider-gateway")
public interface PayFeignApi {
  @PostMapping("/pay")
  ResultData<String> addPay(@RequestBody PayDTO payDTO);

  @DeleteMapping("/pay/{id}")
  ResultData<String> deletePay(@PathVariable("id") Integer id);

  @PutMapping("/pay")
  ResultData<String> updatePay(@RequestBody PayDTO payDTO);

  @GetMapping("/pay/{id}")
  ResultData<PayDTO> getPayById(@PathVariable("id") Integer id);

  @GetMapping("/pay")
  ResultData<List<PayDTO>> getAllPay();

  @GetMapping("module/info")
  ResultData<String> getMessage();

  @GetMapping(value = "/pay/circuit/{id}")
  String myCircuit(@PathVariable("id") Integer id);

  @GetMapping(value = "/pay/bulkhead/{id}")
  String myBulkhead(@PathVariable("id") Integer id);

  @GetMapping(value = "/pay/rate-limit/{id}")
  String myRateLimit(@PathVariable("id") Integer id);

  @GetMapping(value = "/pay/micrometer/{id}")
  String myMicrometer(@PathVariable("id") Integer id);

  @GetMapping(value = "/pay/gateway/get/{id}")
  ResultData<PayDTO> getById(@PathVariable("id") Integer id);

  @GetMapping(value = "/pay/gateway/info")
  ResultData<String> getGatewayInfo();
}
