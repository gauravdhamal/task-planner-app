package com.task.planner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Task Planner REST API", version = "1.0"))
public class TaskPlannerBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskPlannerBackendApplication.class, args);
	}

}