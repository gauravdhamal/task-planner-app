package com.task.planner.dtos;

import com.task.planner.enums.Priority;
import com.task.planner.enums.Status;
import com.task.planner.enums.Type;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

	private Integer taskId;

	@Enumerated(EnumType.STRING)
	private Type type;

	@NotNull(message = "Description must not be null.")
	@NotBlank(message = "Description must not be blank.")
	private String description;

	@Enumerated(EnumType.STRING)
	private Status status;

	@Enumerated(EnumType.STRING)
	private Priority priority;

	@NotNull(message = "Comment must not be null.")
	@NotBlank(message = "Comment must not be blank.")
	private String comment;

}
