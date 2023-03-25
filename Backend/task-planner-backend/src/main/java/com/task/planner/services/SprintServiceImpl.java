package com.task.planner.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.planner.dtos.SprintDTO;
import com.task.planner.dtos.TaskDTO;
import com.task.planner.entities.Sprint;
import com.task.planner.entities.Task;
import com.task.planner.exceptions.NoRecordFoundException;
import com.task.planner.repositories.SprintRepository;
import com.task.planner.repositories.TaskRepository;

@Service
public class SprintServiceImpl implements SprintService {

	@Autowired
	private SprintRepository sprintRepository;

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public SprintDTO createSprint(SprintDTO sprintDTO) {
		Sprint sprint = modelMapper.map(sprintDTO, Sprint.class);
		sprint = sprintRepository.save(sprint);
		sprintDTO = modelMapper.map(sprint, SprintDTO.class);
		return sprintDTO;
	}

	@Override
	public SprintDTO getSprintById(Integer sprintId) throws NoRecordFoundException {
		Sprint sprint = sprintRepository.findById(sprintId)
				.orElseThrow(() -> new NoRecordFoundException("Sprint not found with Id : " + sprintId));
		SprintDTO sprintDTO = modelMapper.map(sprint, SprintDTO.class);
		return sprintDTO;
	}

	@Override
	public SprintDTO updateSprint(Integer sprintId, SprintDTO sprintDTO) throws NoRecordFoundException {
		Sprint sprint = sprintRepository.findById(sprintId)
				.orElseThrow(() -> new NoRecordFoundException("Sprint not found with Id : " + sprintId));
		if (sprintDTO.getName() != null) {
			sprint.setName(sprintDTO.getName());
		}
		if (sprintDTO.getStartDate() != null) {
			sprint.setStartDate(sprintDTO.getStartDate());
		}
		if (sprintDTO.getEndDate() != null) {
			sprint.setEndDate(sprintDTO.getEndDate());
		}
		sprintDTO = modelMapper.map(sprint, SprintDTO.class);
		return sprintDTO;
	}

	@Override
	public String deleteSprint(Integer sprintId) throws NoRecordFoundException {
		Sprint sprint = sprintRepository.findById(sprintId)
				.orElseThrow(() -> new NoRecordFoundException("Sprint not found with Id : " + sprintId));
		sprintRepository.delete(sprint);
		return "Sprint removed from database with Id : " + sprintId + ". Removed from database.";
	}

	@Override
	public List<SprintDTO> getAllSprints() throws NoRecordFoundException {
		List<Sprint> sprints = sprintRepository.findAll();
		if (sprints.isEmpty()) {
			throw new NoRecordFoundException("No any sprint found in database.");
		} else {
			List<SprintDTO> sprintDTOs = new ArrayList<>();
			for (Sprint sprint : sprints) {
				sprintDTOs.add(modelMapper.map(sprint, SprintDTO.class));
			}
			return sprintDTOs;
		}
	}

	@Override
	public String assignTaskToSprint(Integer taskId, Integer sprintId) throws NoRecordFoundException {
		Sprint sprint = sprintRepository.findById(sprintId)
				.orElseThrow(() -> new NoRecordFoundException("Sprint not found with Id : " + sprintId));
		Task task = taskRepository.findById(taskId)
				.orElseThrow(() -> new NoRecordFoundException("Task not found with Id : " + taskId));
		if (task.getSprint() != null) {
			throw new NoRecordFoundException(
					"This task already belongs to Sprint with Id : " + task.getSprint().getSprintId());
		} else {
			task.setSprint(sprint);
		}
		sprint.getTasks().add(task);
		sprintRepository.save(sprint);
		taskRepository.save(task);
		return "Task having Id : " + taskId + ". added to Sprint having Id : " + sprintId;
	}

	@Override
	public String removeTaskFromSprint(Integer taskId, Integer sprintId) throws NoRecordFoundException {
		Sprint sprint = sprintRepository.findById(sprintId)
				.orElseThrow(() -> new NoRecordFoundException("Sprint not found with Id : " + sprintId));
		Task oldTask = taskRepository.findById(taskId)
				.orElseThrow(() -> new NoRecordFoundException("Task not found with Id : " + taskId));
		List<Task> tasks = sprint.getTasks();
		if (sprint.getSprintId() == oldTask.getSprint().getSprintId()) {
			tasks.removeIf(task -> task.getTaskId() == task.getTaskId());
			oldTask.setSprint(null);
			sprintRepository.save(sprint);
			return "Task removed from Sprint with Id : " + sprintId;
		} else {
			throw new NoRecordFoundException("Task does not belongs to Sprint with Id : " + sprintId);
		}
	}

	@Override
	public List<TaskDTO> getAllTasksFromSprint(Integer sprintId) throws NoRecordFoundException {
		Sprint sprint = sprintRepository.findById(sprintId)
				.orElseThrow(() -> new NoRecordFoundException("Sprint not found with Id : " + sprintId));
		List<Task> tasks = taskRepository.findBySprintId(sprint.getSprintId());
		if (tasks.isEmpty()) {
			throw new NoRecordFoundException("No ant task found under sprint with Id : " + sprintId);
		} else {
			List<TaskDTO> taskDTOs = new ArrayList<>();
			for (Task task : tasks) {
				taskDTOs.add(modelMapper.map(task, TaskDTO.class));
			}
			return taskDTOs;
		}
	}

}
