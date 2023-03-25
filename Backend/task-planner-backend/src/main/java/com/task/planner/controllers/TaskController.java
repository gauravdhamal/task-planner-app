package com.task.planner.controllers;

import java.util.List;

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
import com.task.planner.entities.Sprint;
import com.task.planner.entities.User;
import com.task.planner.exceptions.NoRecordFoundException;
import com.task.planner.services.TaskService;

@RestController
@RequestMapping("/tasks")
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

	@GetMapping("/all")
	public ResponseEntity<List<TaskDTO>> getAllTasks() throws NoRecordFoundException {
		List<TaskDTO> taskDTOs = taskService.getAllTasks();
		return new ResponseEntity<List<TaskDTO>>(taskDTOs, HttpStatus.OK);
	}

	@GetMapping("/sprint/{taskId}")
	public ResponseEntity<Sprint> getSprintByTaskId(@PathVariable("taskId") Integer taskId)
			throws NoRecordFoundException {
		Sprint sprint = taskService.getSprintByTaskId(taskId);
		return new ResponseEntity<Sprint>(sprint, HttpStatus.OK);
	}

	@GetMapping("/user/{taskId}")
	public ResponseEntity<User> getUserByTaskId(@PathVariable("taskId") Integer taskId) throws NoRecordFoundException {
		User user = taskService.getUserByTaskId(taskId);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

}
