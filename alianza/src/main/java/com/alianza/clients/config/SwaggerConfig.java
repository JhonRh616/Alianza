package com.alianza.clients.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI myOpenAPI() {
		Contact contact = new Contact();
		contact.setEmail("jhonrh616@gmail.com");
		contact.setName("Jhon Romero");
		contact.setUrl("https://github.com/JhonRh616/Alianza");

		Info info = new Info().title("Alianza Clientes API").version("1.0").contact(contact)
				.description("Prueba técnica de desarrollo backend para Alianza");

		return new OpenAPI().info(info);
	}
}
