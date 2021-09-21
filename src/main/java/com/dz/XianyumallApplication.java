package com.dz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement(proxyTargetClass = true)
@SpringBootApplication
public class XianyumallApplication {

    public static void main(String[] args) {
        SpringApplication.run(XianyumallApplication.class, args);
    }

}
