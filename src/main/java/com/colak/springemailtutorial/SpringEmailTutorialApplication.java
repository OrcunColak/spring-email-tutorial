package com.colak.springemailtutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringEmailTutorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEmailTutorialApplication.class, args);
	}

}
