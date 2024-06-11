package io.purexua.learn.controller;

import io.purexua.learn.api.PayFeignApi;
import io.purexua.learn.entity.PayDTO;
import io.purexua.learn.resp.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class OrderController {
  private final PayFeignApi payFeignApi;

  @Autowired
  public OrderController(PayFeignApi payFeignApi) {
    this.payFeignApi = payFeignApi;
  }

  @GetMapping("/feign/pay/add")
  public ResultData<String> addOrder(PayDTO payDTO) {
    payFeignApi.addPay(payDTO);
    return ResultData.success("order feign module add pay success");
  }

  @GetMapping("/feign/pay/delete/{id}")
  public ResultData<String> deletePay(@PathVariable("id") Integer id) {
    payFeignApi.deletePay(id);
    return ResultData.success("order feign module delete pay success");
  }

  @GetMapping("/feign/pay/update")
  public String updatePay(@RequestBody PayDTO payDTO) {
    payFeignApi.updatePay(payDTO);
    return "order feign module update pay success";
  }

  @GetMapping("/feign/pay/get/{id}")
  public ResultData<String> getPayInfo(@PathVariable("id") Integer id) {
    payFeignApi.getPayById(id);
    return ResultData.success("order feign module get pay success");
  }

  @GetMapping("/feign/pay/get/all")
  public ResultData<String> getAllPayInfo() {
    payFeignApi.getAllPay();
    return ResultData.success("order feign module get all pay success");
  }

  @GetMapping(value = "/feign/pay/module/info")
  private ResultData<String> getInfoByConsul() {
    System.out.println(payFeignApi.getMessage());
    return ResultData.success("order feign module get info success");
  }

}
