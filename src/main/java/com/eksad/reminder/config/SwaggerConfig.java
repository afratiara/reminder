package com.eksad.reminder.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Collections;

@EnableSwagger2
@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {
	
	public Docket eksadAPI() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.eksad.reminder"))
				.paths(regex("/api.*"))
				.build()
				.apiInfo(metaInfo())
				.tags(
						new Tag("Reminder", "Reminder Management API"),
						new Tag("Get Data API", ""),
						new Tag("Data Manipulation API", "Save, Update, and Delete Data Reminder")
						);
	}
	
	private ApiInfo metaInfo() {
		ApiInfo apiInfo = new ApiInfo("Reminder Data", 
									"Collection for Reminder Data", 
									"1.0.0", 
									"http://google.com",
									new Contact("Afra Tiara Rahmayani", "afratiara", "rahmayanitiaraafra@gmail.com"),
									"Afra 2.0",
									"http://www.google.com", 
									Collections.emptyList());
		
		return apiInfo;
	}

}
