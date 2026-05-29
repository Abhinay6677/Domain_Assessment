package com.pharmacy.medicine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MedicineServiceEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicineServiceEurekaApplication.class, args);
	}

}
