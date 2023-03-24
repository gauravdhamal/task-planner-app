package com.task.planner.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SprintDTO {

	private Integer sprintId;

	private String name;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

}