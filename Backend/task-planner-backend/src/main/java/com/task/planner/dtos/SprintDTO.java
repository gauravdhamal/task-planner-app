package com.task.planner.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SprintDTO {

	private Integer sprintId;

	@NotNull(message = "Name must not be null.")
	@NotBlank(message = "Name must not be blank.")
	private String name;

	private LocalDate startDate;

	private LocalDate endDate;

}