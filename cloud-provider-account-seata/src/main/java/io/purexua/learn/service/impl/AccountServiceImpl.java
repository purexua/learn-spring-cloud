package io.purexua.learn.service.impl;

import io.purexua.learn.mapper.AccountMapper;
import io.purexua.learn.service.AccountService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService
{
  @Resource
  AccountMapper accountMapper;

  @Override
  public void decrease(Long userId, Long money) {
    log.info("------->account-service中扣减账户余额开始");
    accountMapper.decrease(userId,money);
    //myTimeOut(); int age = 10/0;
    log.info("------->account-service中扣减账户余额结束");
  }

  /**
   * 模拟超时异常，全局事务回滚
   */
  private static void myTimeOut()
  {
    try { TimeUnit.SECONDS.sleep(65); } catch (InterruptedException e) { e.printStackTrace(); }
  }
}
