package com.restapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
public class SwaggerConfig {

	@Bean
	public Docket geDocket() {
		return new Docket(DocumentationType.SWAGGER_2).select().paths(regex("/User.*")).build().apiInfo(getApiInfo());
	}

	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder().title("User API").description("This api is used to perform CRUD on User...")
				.license("License Info")
				.contact(new Contact("User", "http://localhost:8081/User/All", "user@gmail.com")).version("1.0").build();
	}

}
