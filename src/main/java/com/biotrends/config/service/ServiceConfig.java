package com.biotrends.config.Service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Oscar Malagon
 * @since 08/12/2016
 */
@Configuration
@ComponentScan("com.biotrends.services.*")
@SuppressWarnings("squid:S2094")
public class ServiceConfig {
}
