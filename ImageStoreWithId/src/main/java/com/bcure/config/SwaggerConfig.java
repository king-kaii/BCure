//package com.bcure.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//
//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2).select()
//				.apis(RequestHandlerSelectors.basePackage("com.bcure.controller")) // Replace with your controller
//																					// package
//				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
//	}
//
//	private ApiInfo apiInfo() {
//		return new ApiInfoBuilder().title("Your API Documentation")
//				.description("API documentation for your Spring Boot application").version("1.0").build();
//	}
//
////	@Bean
////	public Docket api() {
////		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
////				.paths(PathSelectors.any()).build();
////	}
////
//////    private ApiInfo apiInfo() {
//////        return new ApiInfoBuilder()
//////            .title("ImageUpload With ID.. API Documentation")
//////            .description("API documentation for your Spring Boot application")
//////            .version("3.0")
//////            .build();
//////    }
//
//}
