package com.bigrestaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackages = {"com.bigrestaurant"})
public class BigRestaurantServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BigRestaurantServiceApplication.class, args);
	}
}
