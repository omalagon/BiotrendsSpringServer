package com.biotrends.config.api;

import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Oscar Malagon
 * @since 2/01/2017.
 */
@Configuration
@EnableSwagger2
public class DocsConfig extends WebMvcConfigurerAdapter {

    public DocsConfig(){
        super();
    }


    @Bean
    public Docket mainConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
            .select().apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            .directModelSubstitute(LocalDate.class, String.class)
            .genericModelSubstitutes(ResponseEntity.class)
            .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo =
            new ApiInfo("Biotrends API Documentation",
                "Biotrends software to manage purchases",
                "2.0",
                "",
                "Oscar Darío Malagón Murcia",
                "",
                "");
        return apiInfo;
    }

}
