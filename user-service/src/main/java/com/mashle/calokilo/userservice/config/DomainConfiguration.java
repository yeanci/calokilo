package com.mashle.calokilo.userservice.config;

import com.mashle.calokilo.userservice.domain.shared.DomainService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = { "com.mashle.calokilo.userservice" },
        includeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = { DomainService.class }) }
)
public class DomainConfiguration {
}
