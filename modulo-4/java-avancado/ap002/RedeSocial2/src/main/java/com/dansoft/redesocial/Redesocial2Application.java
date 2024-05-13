package com.dansoft.redesocial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@EnableCaching
@OpenAPIDefinition(info = @Info(title = "Rede Social API", version = "1", description = "API de uma Rede Social"))
public class Redesocial2Application {
	public static void main(String[] args) {
		SpringApplication.run(Redesocial2Application.class, args);
		log.info("Aplicação iniciada.");	}
}
