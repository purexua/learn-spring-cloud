package io.purexua.learn.controller;

import io.purexua.learn.Service.FlowLimitService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class FlowLimitController {

  @GetMapping("/testA")
  public String testA() {
    return "------testA";
  }

  @GetMapping("/testB")
  public String testB() {
    return "------testB";
  }

  @Resource
  private FlowLimitService flowLimitService;

  @GetMapping("/testC")
  public String testC() {
    flowLimitService.common();
    return "------testC";
  }

  @GetMapping("/testD")
  public String testD() {
    flowLimitService.common();
    return "------testD";
  }

  @GetMapping("/testE")
  public String testE() {
    System.out.println(System.currentTimeMillis() + "      testE,排队等待");
    return "------testE";
  }

  @GetMapping("/testF")
  public String testF() {
    //暂停几秒钟线程
    try {
      TimeUnit.SECONDS.sleep(1);
    } catch (InterruptedException e) {
      System.out.println("----测试:新增熔断规则-慢调用比例,异常 " + e.getMessage());
    }
    System.out.println("----测试:新增熔断规则-慢调用比例 ");
    return "------testF 新增熔断规则-慢调用比例";
  }

  @GetMapping("/testG")
  public String testG() {
    System.out.println("----测试:新增熔断规则-异常比例 ");
    int age = 10 / 0;
    return "------testG,新增熔断规则-异常比例 ";
  }

  @GetMapping("/testH")
  public String testH() {
    System.out.println("----测试:新增熔断规则-异常数 ");
    int age = 10 / 0;
    return "------testH,新增熔断规则-异常数 ";
  }
}
