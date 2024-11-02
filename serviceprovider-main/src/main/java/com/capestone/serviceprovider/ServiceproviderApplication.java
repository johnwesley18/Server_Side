package com.capestone.serviceprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceproviderApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceproviderApplication.class, args);
	}

}
