package io.purexua.learn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("io.purexua.learn.mapper") //import tk.mybatis.spring.annotation.MapperScan;
@SpringBootApplication
public class Main2003 {
  public static void main(String[] args) {
    SpringApplication.run(Main2003.class, args);
  }
}