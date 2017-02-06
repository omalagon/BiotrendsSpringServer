package com.biotrends;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import lombok.extern.slf4j.Slf4j;

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
