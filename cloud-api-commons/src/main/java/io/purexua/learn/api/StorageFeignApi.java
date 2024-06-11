package io.purexua.learn.api;

import io.purexua.learn.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "cloud-provider-storage-seata")
public interface StorageFeignApi {
  @PostMapping(value = "/storage/decrease")
  ResultData<String> decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);
}
