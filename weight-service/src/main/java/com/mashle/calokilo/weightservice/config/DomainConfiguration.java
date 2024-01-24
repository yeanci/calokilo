package com.mashle.calokilo.weightservice.config;

import com.mashle.calokilo.weightservice.domain.shared.DomainService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = { "com.mashle.calokilo.weightservice" },
        includeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = { DomainService.class }) }
)
public class DomainConfiguration {
}
