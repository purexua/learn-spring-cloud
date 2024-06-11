package io.purexua.learn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("io.purexua.learn.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class Main2001 {
  public static void main(String[] args) {
    SpringApplication.run(Main2001.class, args);
  }
}