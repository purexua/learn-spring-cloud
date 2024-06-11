package io.purexua.learn.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.purexua.learn.api.PayFeignApi;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@RestController
public class OrderCircuitController {
  @Resource
  private PayFeignApi payFeignApi;

  @GetMapping(value = "/feign/pay/circuit/{id}")
  @CircuitBreaker(name = "cloud-provider-payment", fallbackMethod = "myCircuitFallback")
  public String myCircuitBreaker(@PathVariable("id") Integer id) {
    return payFeignApi.myCircuit(id);
  }

  public String myCircuitFallback(Integer id, Throwable t) {
    System.out.println("myCircuitFallback");
    return "myCircuitFallback，系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~";
  }

  @GetMapping(value = "/feign/pay/bulkhead/{id}")
  @Bulkhead(name = "cloud-provider-payment", fallbackMethod = "myBulkheadFallback", type = Bulkhead.Type.SEMAPHORE)
  public String myBulkhead(@PathVariable("id") Integer id) {
    return payFeignApi.myBulkhead(id);
  }

  public String myBulkheadFallback(Integer id, Throwable t) {
    System.out.println("myBulkheadFallback");
    return "myBulkheadFallback，隔板超出最大数量限制，系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~";
  }

  @GetMapping(value = "/feign/pay/bulkhead-pool/{id}")
  @Bulkhead(name = "cloud-payment-service", fallbackMethod = "myBulkheadPoolFallback", type = Bulkhead.Type.THREADPOOL)
  public CompletableFuture<String> myBulkheadTHREADPOOL(@PathVariable("id") Integer id) {
    System.out.println(Thread.currentThread().getName() + "\t" + "enter the method!!!");
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      System.out.println("线程 catch 异常");
    }
    System.out.println(Thread.currentThread().getName() + "\t" + "exist the method!!!");

    return CompletableFuture.supplyAsync(() -> payFeignApi.myBulkhead(id) + "\t" + " Bulkhead.Type.THREADPOOL");
  }

  public CompletableFuture<String> myBulkheadPoolFallback(Integer id, Throwable t) {
    return CompletableFuture.supplyAsync(() -> "Bulkhead.Type.THREADPOOL，系统繁忙，请稍后再试-----/(ㄒoㄒ)/~~");
  }

  @GetMapping(value = "/feign/pay/rate-limit/{id}")
  @RateLimiter(name = "cloud-payment-service", fallbackMethod = "myRateLimitFallback")
  public String myBulkheadRateLimit(@PathVariable("id") Integer id) {
    return payFeignApi.myRateLimit(id);
  }

  public String myRateLimitFallback(Integer id, Throwable t) {
    return "你被限流了，禁止访问/(ㄒoㄒ)/~~";
  }
}
