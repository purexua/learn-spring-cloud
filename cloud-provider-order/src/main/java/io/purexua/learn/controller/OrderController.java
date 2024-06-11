package io.purexua.learn.controller;

import io.purexua.learn.entity.PayDTO;
import io.purexua.learn.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class OrderController {
  public static final String PaymentSrv_URL = "http://cloud-provider-payment";

  private final RestTemplate restTemplate;

  private final DiscoveryClient discoveryClient;

  @Autowired
  public OrderController(RestTemplate restTemplate, DiscoveryClient discoveryClient) {
    this.restTemplate = restTemplate;
    this.discoveryClient = discoveryClient;
  }


  @GetMapping("/consumer/pay/add")
  public ResultData<String> addOrder(PayDTO payDTO) {
    restTemplate.postForObject(PaymentSrv_URL + "/pay", payDTO, ResultData.class);
    return ResultData.success("order module add pay success");
  }

  @GetMapping("/consumer/pay/delete/{id}")
  public ResultData<String> deletePay(@PathVariable("id") Integer id) {
    restTemplate.delete(PaymentSrv_URL + "/pay/" + id);
    return ResultData.success("order module delete pay success");
  }

  @GetMapping("/consumer/pay/update")
  public String updatePay(@RequestBody PayDTO payDTO) {
    restTemplate.put(PaymentSrv_URL + "/pay", payDTO);
    return "order module update pay success";
  }

  @GetMapping("/consumer/pay/get/{id}")
  public ResultData<String> getPayInfo(@PathVariable("id") Integer id) {
    restTemplate.getForObject(PaymentSrv_URL + "/pay/" + id, ResultData.class, id);
    return ResultData.success("order module get pay success");
  }

  @GetMapping("/consumer/pay/get/all")
  public ResultData<String> getAllPayInfo() {
    restTemplate.getForObject(PaymentSrv_URL + "/pay", ResultData.class);
    return ResultData.success("order module get all pay success");
  }

  @GetMapping(value = "/consumer/pay/module/info")
  private ResultData<String> getInfoByConsul() {
    System.out.println(restTemplate.getForObject(PaymentSrv_URL + "/module/info", ResultData.class));
    return ResultData.success("order module get info success");
  }

  @GetMapping("/consumer/discovery")
  public String discovery() {
    List<String> services = discoveryClient.getServices();
    for (String element : services) {
      System.out.println(element);
    }
    System.out.println("===================================");
    List<ServiceInstance> instances = discoveryClient.getInstances("cloud-provider-payment");
    for (ServiceInstance element : instances) {
      System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t" + element.getUri());
    }
    return instances.get(0).getServiceId() + ":" + instances.get(0).getPort();
  }

}
