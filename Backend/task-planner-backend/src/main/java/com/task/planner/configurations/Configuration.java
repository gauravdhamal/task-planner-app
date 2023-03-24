package com.task.planner.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class Configuration {

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

}
