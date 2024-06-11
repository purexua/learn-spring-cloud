package io.purexua.learn.api;

import io.purexua.learn.resp.ResultData;
import io.purexua.learn.resp.ReturnCodeEnum;
import org.springframework.stereotype.Component;

@Component
public class PayFeignSentinelApiFallBack implements PayFeignSentinelApi {
  @Override
  public ResultData<String> getPayByOrderNo(String orderNo) {
    return ResultData.fail(ReturnCodeEnum.RC500.getCode(), "对方服务宕机或不可用，FallBack服务降级o(╥﹏╥)o");
  }
}
