package com.mashle.calokilo.weightservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class WeightServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeightServiceApplication.class, args);
	}

}
