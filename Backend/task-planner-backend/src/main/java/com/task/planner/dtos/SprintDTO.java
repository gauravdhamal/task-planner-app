package com.task.planner.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SprintDTO {

	private Integer sprintId;

	private String name;

	private LocalDate startDate;

	private LocalDate endDate;

}