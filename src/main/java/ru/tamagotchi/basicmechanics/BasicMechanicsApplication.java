package ru.tamagotchi.basicmechanics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class BasicMechanicsApplication {
	public static void main(String[] args) {
		SpringApplication.run(BasicMechanicsApplication.class, args);
	}
}
