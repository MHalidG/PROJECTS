package com.hillrent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class HillRentApplication {

	public static void main(String[] args) {
		SpringApplication.run(HillRentApplication.class, args);
	}

	
	//Origin= http://localhost:3000  
		//protokol://domain:port
		
		//SameOriginPolicy Front End origin adresi backendde belirlenen Origin adresinden baska adrese istek gonderemez.
		//CrossOriginPolicy FrontEnd baska bir portta backendde baska portta calisiyor.
		
		//Origin= http://localhost:8080
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
			}
		};
	}

	
	
}
