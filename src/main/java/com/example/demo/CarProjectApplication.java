package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class CarProjectApplication {

	public static void main(String[] args) {
		ApplicationContext beanBag = SpringApplication.run(CarProjectApplication.class, args);
		System.out.println(beanBag.getBean("time", String.class));
	}

}
