package io.purexua.learn.controller;

import io.purexua.learn.entity.Pay;
import io.purexua.learn.entity.PayDTO;
import io.purexua.learn.resp.ResultData;
import io.purexua.learn.service.PayService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PayController {
  private final PayService payService;

  @Autowired
  public PayController(PayService payService) {
    this.payService = payService;
  }

  @PostMapping("/pay")
  public ResultData<String> addPay(@RequestBody PayDTO payDTO) {
    Pay pay = new Pay();
    BeanUtils.copyProperties(payDTO, pay, Pay.class);
    System.out.println("addPay: " + pay);
    int i = payService.add(pay);
    return ResultData.success("成功添加记录，返回值：" + i);
  }

  @DeleteMapping("/pay/{id}")
  public ResultData<String> deletePay(@PathVariable("id") Integer id) {
    System.out.println("deletePay: " + id);
    int i = payService.delete(id);
    return ResultData.success("成功删除记录，返回值：" + i);
  }

  @PutMapping("/pay")
  public ResultData<String> updatePay(@RequestBody PayDTO payDTO) {
    Pay pay = new Pay();
    BeanUtils.copyProperties(payDTO, pay, Pay.class);
    System.out.println("updatePay: " + pay);
    int i = payService.update(pay);
    return ResultData.success("成功修改记录，返回值：" + i);
  }

  @GetMapping("/pay/{id}")
  public ResultData<PayDTO> getPayById(@PathVariable("id") Integer id) {
    System.out.println("getPay: " + id);
    Pay pay = payService.get(id);
    PayDTO payDTO = new PayDTO();
    BeanUtils.copyProperties(pay, payDTO, PayDTO.class);
    return ResultData.success(payDTO);
  }

  @GetMapping("/pay")
  public ResultData<List<PayDTO>> getAllPay() {
    System.out.println("getAllPay");
    List<Pay> pays = payService.getAllPay();
    List<PayDTO> payDTOList = pays.stream().map(pay -> {
      PayDTO payDTO = new PayDTO();
      BeanUtils.copyProperties(pay, payDTO, PayDTO.class);
      return payDTO;
    }).toList();
    return ResultData.success(payDTOList);
  }

  @Value("${server.port}")
  private String port;
  @Value("${user.username}")
  private String username;
  @Value("${user.from}")
  private String from;

  @GetMapping("module/info")
  public ResultData<String> getMessage() {
    return ResultData.success("username: " + username + ", from: " + from + ", port: " + port);
  }

}
