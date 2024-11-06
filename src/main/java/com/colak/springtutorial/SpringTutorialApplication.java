package com.colak.springtutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringTutorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTutorialApplication.class, args);
	}

}
