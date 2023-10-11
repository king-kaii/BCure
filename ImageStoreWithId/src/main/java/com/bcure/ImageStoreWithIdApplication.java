package com.bcure;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ImageStoreWithIdApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageStoreWithIdApplication.class, args);
	}

	@Bean
	ModelMapper modelMapper() {

		return new ModelMapper();
	}

}
