package io.purexua.learn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("io.purexua.learn.mapper")
@RefreshScope
public class Main8001 {
    public static void main(String[] args) {
        SpringApplication.run(Main8001.class, args);
    }
}