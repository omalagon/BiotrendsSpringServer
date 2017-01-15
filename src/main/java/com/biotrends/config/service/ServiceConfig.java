package com.biotrends.config.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Oscar Malagon
 * @since 08/12/2016
 */
@Configuration
@ComponentScan("com.biotrends.services.*")
@SuppressWarnings("squid:S2094")
public class ServiceConfig {
}
