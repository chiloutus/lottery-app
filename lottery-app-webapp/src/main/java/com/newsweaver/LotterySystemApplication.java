package com.newsweaver;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class LotterySystemApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(LotterySystemApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(LotterySystemApplication.class);
	}

	@Bean
	public Docket swaggerSpringMvcPlugin() {
		final ApiInfo info = new ApiInfo(
				"Lottery System API",
				"",
				"0.0.1",
				"http://terms-of-service.url",
				"lynamgaz@yahoo.ie",
				"License",
				"http://license-url");
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("business-api")
				.select()
				.paths(paths())
				.build()
				.apiInfo(info);
	}
	private Predicate<String> paths() {
		// any api that matches one of these paths
		return Predicates.or(PathSelectors.regex("/v1/.*"));
	}

}
