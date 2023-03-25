package com.task.planner.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.planner.dtos.TaskDTO;
import com.task.planner.dtos.UserDTO;
import com.task.planner.exceptions.NoRecordFoundException;
import com.task.planner.services.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/create")
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
		userDTO = userService.createUser(userDTO);
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.ACCEPTED);
	}

	@GetMapping("/get/{userId}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("userId") Integer userId) throws NoRecordFoundException {
		UserDTO userDTO = userService.getUserById(userId);
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.ACCEPTED);
	}

	@PutMapping("/update/{userId}")
	public ResponseEntity<UserDTO> updateUser(@PathVariable("userId") Integer userId, @RequestBody UserDTO userDTO)
			throws NoRecordFoundException {
		userDTO = userService.updateUser(userId, userDTO);
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable("userId") Integer userId) throws NoRecordFoundException {
		String message = userService.deleteUser(userId);
		return new ResponseEntity<String>(message, HttpStatus.ACCEPTED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<UserDTO>> getAllUsers() throws NoRecordFoundException {
		List<UserDTO> dtos = userService.getAllUsers();
		return new ResponseEntity<List<UserDTO>>(dtos, HttpStatus.OK);
	}

	@GetMapping("/tasks/{userId}")
	public ResponseEntity<List<TaskDTO>> getAllTaskByUserId(@PathVariable("userId") Integer userId)
			throws NoRecordFoundException {
		List<TaskDTO> taskDTOs = userService.getAllTaskByUserId(userId);
		return new ResponseEntity<List<TaskDTO>>(taskDTOs, HttpStatus.OK);
	}

	@PostMapping("assignTaskToUser/{userId}/{taskId}")
	public ResponseEntity<String> assignTaskToUser(@PathVariable("userId") Integer userId,
			@PathVariable("taskId") Integer taskId) throws NoRecordFoundException {
		String message = userService.assignTaskToUser(userId, taskId);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@PostMapping("removeTaskFromUser/{userId}/{taskId}")
	public ResponseEntity<String> removeTaskFromUser(@PathVariable("userId") Integer userId,
			@PathVariable("taskId") Integer taskId) throws NoRecordFoundException {
		String message = userService.removeTaskFromUser(userId, taskId);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

}
