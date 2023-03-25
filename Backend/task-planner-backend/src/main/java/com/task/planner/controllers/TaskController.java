package com.task.planner.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.planner.dtos.SprintDTO;
import com.task.planner.dtos.TaskDTO;
import com.task.planner.dtos.UserDTO;
import com.task.planner.enums.Priority;
import com.task.planner.enums.Status;
import com.task.planner.exceptions.NoRecordFoundException;
import com.task.planner.services.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@PostMapping("/create")
	public ResponseEntity<TaskDTO> createTask(@RequestBody @Valid TaskDTO taskDTO) {
		taskDTO = taskService.createTask(taskDTO);
		return new ResponseEntity<TaskDTO>(taskDTO, HttpStatus.ACCEPTED);
	}

	@GetMapping("/get/{taskId}")
	public ResponseEntity<TaskDTO> getTaskById(@PathVariable("taskId") Integer taskId) throws NoRecordFoundException {
		TaskDTO taskDTO = taskService.getTaskById(taskId);
		return new ResponseEntity<TaskDTO>(taskDTO, HttpStatus.ACCEPTED);
	}

	@PutMapping("/update/{taskId}")
	public ResponseEntity<TaskDTO> updateTask(@PathVariable("taskId") Integer taskId,
			@RequestBody @Valid TaskDTO taskDTO) throws NoRecordFoundException {
		taskDTO = taskService.updateTask(taskId, taskDTO);
		return new ResponseEntity<TaskDTO>(taskDTO, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{taskId}")
	public ResponseEntity<String> deleteTask(@PathVariable("taskId") Integer taskId) throws NoRecordFoundException {
		String result = taskService.deleteTask(taskId);
		return new ResponseEntity<String>(result, HttpStatus.ACCEPTED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<TaskDTO>> getAllTasks() throws NoRecordFoundException {
		List<TaskDTO> taskDTOs = taskService.getAllTasks();
		return new ResponseEntity<List<TaskDTO>>(taskDTOs, HttpStatus.OK);
	}

	@GetMapping("/sprint/{taskId}")
	public ResponseEntity<SprintDTO> getSprintByTaskId(@PathVariable("taskId") Integer taskId)
			throws NoRecordFoundException {
		SprintDTO sprintDTO = taskService.getSprintByTaskId(taskId);
		return new ResponseEntity<SprintDTO>(sprintDTO, HttpStatus.OK);
	}

	@GetMapping("/user/{taskId}")
	public ResponseEntity<UserDTO> getUserByTaskId(@PathVariable("taskId") Integer taskId)
			throws NoRecordFoundException {
		UserDTO userDTO = taskService.getUserByTaskId(taskId);
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
	}

	@PatchMapping("/change/status/{taskId}/{status}")
	public ResponseEntity<String> changeStatusOfTask(@PathVariable("taskId") Integer taskId,
			@PathVariable("status") Status status) throws NoRecordFoundException {
		String message = taskService.changeStatusOfTask(taskId, status);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@PatchMapping("/change/priority/{taskId}/{priority}")
	public ResponseEntity<String> changePriorityOfTask(@PathVariable("taskId") Integer taskId,
			@PathVariable("priority") Priority priority) throws NoRecordFoundException {
		String message = taskService.changePriorityOfTask(taskId, priority);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

}
