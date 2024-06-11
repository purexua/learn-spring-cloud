package io.purexua.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Main8100 {
  public static void main(String[] args) {
    SpringApplication.run(Main8100.class, args);
  }
}