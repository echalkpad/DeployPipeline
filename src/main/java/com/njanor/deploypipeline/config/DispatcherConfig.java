package com.njanor.deploypipeline.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan("com.njanor.deploypipeline")
@EnableWebMvc
public class DispatcherConfig extends WebMvcConfigurerAdapter {

}
