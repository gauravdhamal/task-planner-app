package com.task.planner.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sprint {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer sprintId;

	private String name;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	@OneToMany(mappedBy = "sprint")
	List<Task> tasks = new ArrayList<>();

}
