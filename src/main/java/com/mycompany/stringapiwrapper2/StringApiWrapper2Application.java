package com.mycompany.stringapiwrapper2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StringApiWrapper2Application {

    public static void main(String[] args) {
        SpringApplication.run(StringApiWrapper2Application.class, args);
    }

}
