package com.alianza.clients;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "com.alianza.clients.*" } )
@EnableJpaRepositories(basePackages = { "com.alianza.clients.repository" })
public class AlianzaAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlianzaAppApplication.class, args);
	}

}
