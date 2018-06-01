package com.homedepot.excel.upload;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	@Bean
	  public Docket newApiDocket() {

	    return new Docket(DocumentationType.SWAGGER_2).groupName("THD-excel-upload")
	        .apiInfo(apiInfo()).select().paths(PathSelectors.regex("/*.*")).build();
	  }

	  private ApiInfo apiInfo() {
	    return new ApiInfoBuilder().title("thd-excel-upload")
	        .description("ThresholdManagementWS - Manage threshold timings").version("1.0").build();
	  }
}
