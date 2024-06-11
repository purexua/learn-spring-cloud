package io.purexua.learn.controller;

import io.purexua.learn.resp.ResultData;
import io.purexua.learn.service.AccountService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

  @Resource
  AccountService accountService;

  @PostMapping("/account/decrease")
  public ResultData<String> decrease(@RequestParam("userId") Long userId, @RequestParam("money") Long money) {
    accountService.decrease(userId, money);
    return ResultData.success("扣减账户余额成功！");
  }
}
