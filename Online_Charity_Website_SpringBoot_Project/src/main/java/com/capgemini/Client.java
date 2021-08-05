package com.capgemini;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Client {

	public static void main(String[] args) {
		// step-1 : Get the handle of spring container(ApplicationContext)
		SpringApplication.run(Client.class, args);

	}
}
