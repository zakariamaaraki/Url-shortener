package com.tiny.url.urlconverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class UrlConverterApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlConverterApplication.class, args);
	}

}
