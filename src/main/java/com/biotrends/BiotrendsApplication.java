package com.biotrends;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;

/**
 * @author Oscar Malagon
 * @since 21/12/2016
 */

@Slf4j
@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class BiotrendsApplication  implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(BiotrendsApplication.class, args);
	}

	@Override public void run(String... args) throws Exception {
		log.info("Biotrends - RESTful API is running");
	}

}
