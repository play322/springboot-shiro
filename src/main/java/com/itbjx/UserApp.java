package com.itbjx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.itbjx.mapper")
public class UserApp {
	public static void main(String[] args) {
		SpringApplication.run(UserApp.class,args);
	}
}
