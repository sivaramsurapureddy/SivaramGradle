package com.synergy.recupro;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration;


@SpringBootApplication
@EnableSwagger2
@Import(SpringDataRestConfiguration.class)
@EnableJpaAuditing
@EntityScan(basePackageClasses = {
		RecuProApplication.class,
		Jsr310JpaConverters.class
})
@ComponentScan("com.synergy.*")
public class RecuProApplication {
	// Added this annotation for the time stamp tracking.
	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
	public static void main(String[] args) {
		SpringApplication.run(RecuProApplication.class, args);
	}
}




