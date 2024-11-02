package com.ust.OrdersServiceProvider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
public class OrdersServiceProviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrdersServiceProviderApplication.class, args);
	}

}
