package com.task.planner.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.task.planner.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

	@Query("FROM Task WHERE sprint.sprintId = ?1")
	public List<Task> findBySprintId(Integer sprintId);

	@Query("FROM Task WHERE user.userId = ?1")
	public List<Task> findByUserId(Integer userId);

	@Query("FROM Task WHERE sprint = null")
	public List<Task> findBySprint();

	@Query("FROM Task WHERE sprint = null ORDER BY priority ASC")
	public List<Task> findBySprintOrderByPriorityASC();

	@Query("FROM Task WHERE sprint = null ORDER BY priority DESC")
	public List<Task> findBySprintOrderByPriorityDESC();

}