package com.example.hyzhan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing  // jpa 注入createTime,updateTime
public class ScarecrowApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScarecrowApplication.class, args);
    }

}
