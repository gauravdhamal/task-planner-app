package com.task.planner.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.planner.dtos.SprintDTO;
import com.task.planner.dtos.TaskDTO;
import com.task.planner.dtos.UserDTO;
import com.task.planner.entities.Task;
import com.task.planner.exceptions.NoRecordFoundException;
import com.task.planner.repositories.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public TaskDTO createTask(TaskDTO taskDTO) {
		Task task = modelMapper.map(taskDTO, Task.class);
		task = taskRepository.save(task);
		taskDTO = modelMapper.map(task, TaskDTO.class);
		return taskDTO;
	}

	@Override
	public TaskDTO getTaskById(Integer taskId) throws NoRecordFoundException {
		Task task = taskRepository.findById(taskId)
				.orElseThrow(() -> new NoRecordFoundException("Task not found with Id : " + taskId));
		TaskDTO taskDTO = modelMapper.map(task, TaskDTO.class);
		return taskDTO;
	}

	@Override
	public TaskDTO updateTask(Integer taskId, TaskDTO taskDTO) throws NoRecordFoundException {
		Task task = taskRepository.findById(taskId)
				.orElseThrow(() -> new NoRecordFoundException("Task not found with Id : " + taskId));
		if (taskDTO.getDescription() != null) {
			task.setDescription(taskDTO.getDescription());
		}
		if (taskDTO.getComment() != null) {
			task.setComment(taskDTO.getComment());
		}
		if (taskDTO.getStatus() != null) {
			task.setStatus(taskDTO.getStatus());
		}
		if (taskDTO.getPriority() != null) {
			task.setPriority(taskDTO.getPriority());
		}
		taskRepository.save(task);
		taskDTO = modelMapper.map(task, TaskDTO.class);
		return taskDTO;
	}

	@Override
	public String deleteTask(Integer taskId) throws NoRecordFoundException {
		Task task = taskRepository.findById(taskId)
				.orElseThrow(() -> new NoRecordFoundException("Task not found with Id : " + taskId));
		taskRepository.delete(task);
		return "Task with Id : " + taskId + ". Removed from database.";
	}

	@Override
	public List<TaskDTO> getAllTasks() throws NoRecordFoundException {
		List<Task> tasks = taskRepository.findAll();
		if (tasks.isEmpty()) {
			throw new NoRecordFoundException("No any task found in database.");
		} else {
			List<TaskDTO> taskDTOs = new ArrayList<>();
			for (Task task : tasks) {
				taskDTOs.add(modelMapper.map(task, TaskDTO.class));
			}
			return taskDTOs;
		}
	}

	@Override
	public UserDTO getUserByTaskId(Integer taskId) throws NoRecordFoundException {
		Task task = taskRepository.findById(taskId)
				.orElseThrow(() -> new NoRecordFoundException("Task not found with Id : " + taskId));
		if (task.getUser() == null) {
			throw new NoRecordFoundException("Task does not assigned to any user.");
		} else {
			UserDTO userDTO = modelMapper.map(task.getUser(), UserDTO.class);
			return userDTO;
		}
	}

	@Override
	public SprintDTO getSprintByTaskId(Integer taskId) throws NoRecordFoundException {
		Task task = taskRepository.findById(taskId)
				.orElseThrow(() -> new NoRecordFoundException("Task not found with Id : " + taskId));
		if (task.getSprint() == null) {
			throw new NoRecordFoundException("Task does not belongs to any Sprint.");
		} else {
			SprintDTO sprintDTO = modelMapper.map(task.getSprint(), SprintDTO.class);
			return sprintDTO;
		}
	}

}
