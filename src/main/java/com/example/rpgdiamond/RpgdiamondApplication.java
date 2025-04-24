package com.example.rpgdiamond;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RpgdiamondApplication {

	public static void main(String[] args) {
		SpringApplication.run(RpgdiamondApplication.class, args);
	}

}
