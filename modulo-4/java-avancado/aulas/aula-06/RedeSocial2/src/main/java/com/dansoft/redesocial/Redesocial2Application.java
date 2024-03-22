package com.dansoft.redesocial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Redesocial2Application {
	private static Logger log = LoggerFactory.getLogger(Redesocial2Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Redesocial2Application.class, args);
		log.info("Aplicação iniciada.");	}
}
