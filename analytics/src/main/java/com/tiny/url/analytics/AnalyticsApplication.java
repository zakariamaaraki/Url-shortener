package com.tiny.url.analytics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AnalyticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnalyticsApplication.class, args);
	}

}
