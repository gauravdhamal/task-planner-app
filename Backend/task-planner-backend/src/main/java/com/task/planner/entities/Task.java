package com.task.planner.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.task.planner.enums.Priority;
import com.task.planner.enums.Status;
import com.task.planner.enums.Type;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer taskId;

	@Enumerated(EnumType.STRING)
	private Type type;

	private String description;

	@Enumerated(EnumType.STRING)
	private Status status;

	@Enumerated(EnumType.STRING)
	private Priority priority;

	private String comment;

	/**
	 * Many tasks can belongs to one user. So relation is Many to One which is
	 * mapped by single user entity.
	 */
	@JsonIgnore
	@ManyToOne
	private User user;

	/**
	 * Many tasks can be added to one Sprint. So relation is Many to One which is
	 * mapped by single Sprint entity.
	 */
	@JsonIgnore
	@ManyToOne
	private Sprint sprint;

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", type=" + type + ", description=" + description + ", status=" + status
				+ ", priority=" + priority + ", comment=" + comment + "]";
	}

}
