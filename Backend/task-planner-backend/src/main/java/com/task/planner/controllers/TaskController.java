package com.task.planner.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.planner.dtos.TaskDTO;
import com.task.planner.exceptions.NoRecordFoundException;
import com.task.planner.services.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@PostMapping("/create")
	public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO) {
		taskDTO = taskService.createTask(taskDTO);
		return new ResponseEntity<TaskDTO>(taskDTO, HttpStatus.ACCEPTED);
	}

	@GetMapping("/get/{taskId}")
	public ResponseEntity<TaskDTO> getTaskById(@PathVariable("taskId") Integer taskId) throws NoRecordFoundException {
		TaskDTO taskDTO = taskService.getTaskById(taskId);
		return new ResponseEntity<TaskDTO>(taskDTO, HttpStatus.ACCEPTED);
	}

	@PutMapping("/update/{taskId}")
	public ResponseEntity<TaskDTO> updateTask(@PathVariable("taskId") Integer taskId, @RequestBody TaskDTO taskDTO)
			throws NoRecordFoundException {
		taskDTO = taskService.updateTask(taskId, taskDTO);
		return new ResponseEntity<TaskDTO>(taskDTO, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{taskId}")
	public ResponseEntity<String> deleteTask(@PathVariable("taskId") Integer taskId) throws NoRecordFoundException {
		String result = taskService.deleteTask(taskId);
		return new ResponseEntity<String>(result, HttpStatus.ACCEPTED);
	}

}
