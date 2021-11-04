package com.listou.listou.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2; 

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket listouAPI() { 
		return new Docket(DocumentationType.SWAGGER_2).
				select().
				apis(RequestHandlerSelectors.basePackage("com.listou.listou"))
				.paths(regex("/api.*"))
				.build()
				.apiInfo(metaInfo());
	}
	
	/* Aviso de Raw Type suprimido */
	@SuppressWarnings("rawtypes")
	private ApiInfo metaInfo() {
		return new ApiInfo(
				"Listou API Rest",
				"API Rest para istou APP.",
				"0.1",
				"Terms of Service",
				new Contact("Vinicius Itoi", "" , "viniciusitoi@gmail.com"),
				"Apache License Version 2.0",
				"https://www.apache.org/licenses/LICENSE-2.0.txt",new ArrayList<VendorExtension>());
	}
}
