package com.rsarabia.jtodolistappback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class JToDoListAppBackApplication {
	public static void main(String[] args) {
		SpringApplication.run(JToDoListAppBackApplication.class, args);
	}
}
