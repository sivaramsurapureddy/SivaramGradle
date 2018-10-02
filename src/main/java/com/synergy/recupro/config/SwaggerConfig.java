/**
 * 
 */
package com.synergy.recupro.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author Ahmar
 *
 */
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2)
				.tags(new Tag("Accounts Entity","Repository for Accounts Entity"))
				.tags(new Tag("Candidate Entity","Repository for Candidates Entity"))
				.tags(new Tag("Document Entity","Repository for Documents Entity"))
				.tags(new Tag("ImageModel Entity","Repository for Image Model Entity"))
				.tags(new Tag("Requirements Entity","Repository for Requirements Entity"))
				.tags(new Tag("Role Entity","Repository for Roles Entity"))
				.tags(new Tag("User Entity","Repository for Users Entity"))
				.select()
				.apis(RequestHandlerSelectors.any()).paths(PathSelectors.any())
				.build().pathMapping("/")
				.securitySchemes(Arrays.asList(apiKey()))
				.securityContexts(Arrays.asList(securityContext()))
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Recupro Documentation").build();
	}

	private ApiKey apiKey() {
		return new ApiKey("apiKey", "Authorization", "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth())
				.forPaths(PathSelectors.any()).build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope(
				"global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("apiKey",
				authorizationScopes));
	}

}
