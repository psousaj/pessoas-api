package br.com.dev;

import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
public class SwaggerConfig  {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.dev"))
				.paths(PathSelectors.any()).build();
	}

	private ApiInfo apiInfo() {

		return new ApiInfoBuilder().title("SPRING REST API").description("Documentação das APIs REST")
				.contact(new springfox.documentation.service.Contact("José", "https://github.com/psousaj",
						"josepsousa2012@gmail.com"))
				.build();
	}
}