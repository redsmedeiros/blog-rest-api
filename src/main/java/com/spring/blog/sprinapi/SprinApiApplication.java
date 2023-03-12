package com.spring.blog.sprinapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;

@SpringBootApplication
public class SprinApiApplication {

	@Bean //é uma anotação que indica que um método de uma classe é responsável por criar e configurar um objeto que será gerenciado pelo framework Spring.
	public ModelMapper modelMapper(){ 
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SprinApiApplication.class, args);
	}

}
