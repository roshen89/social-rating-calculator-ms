package com.cybercube;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableCaching
@EnableEurekaClient
public class SocialRatingCalculatorMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialRatingCalculatorMsApplication.class, args);
	}

}
