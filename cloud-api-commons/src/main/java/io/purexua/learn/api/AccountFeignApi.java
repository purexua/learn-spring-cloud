package io.purexua.learn.api;

import io.purexua.learn.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "cloud-provider-account-seata")
public interface AccountFeignApi {
  @PostMapping("/account/decrease")
  ResultData<String> decrease(@RequestParam("userId") Long userId, @RequestParam("money") Long money);
}