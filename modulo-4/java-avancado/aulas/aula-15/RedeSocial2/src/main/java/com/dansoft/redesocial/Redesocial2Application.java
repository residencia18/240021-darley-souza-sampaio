package com.dansoft.redesocial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@EnableCaching
public class Redesocial2Application {
	public static void main(String[] args) {
		SpringApplication.run(Redesocial2Application.class, args);
		log.info("Aplicação iniciada.");	}
}
