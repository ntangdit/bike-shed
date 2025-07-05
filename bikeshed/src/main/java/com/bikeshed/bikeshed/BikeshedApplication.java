package com.bikeshed.bikeshed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class BikeshedApplication {

	public static void main(String[] args) {
		SpringApplication.run(BikeshedApplication.class, args);
	}

}
