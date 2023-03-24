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

import com.task.planner.dtos.UserDTO;
import com.task.planner.exceptions.NoRecordFoundException;
import com.task.planner.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/create")
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
		userDTO = userService.createUser(userDTO);
		return new ResponseEntity<UserDTO>(userDTO, HttpStatus.ACCEPTED);
	}

	@GetMapping("/get/{userId}")
	public ResponseEntity<UserDTO> getUser(@PathVariable("userId") Integer userId) throws NoRecordFoundException {
		UserDTO userDTO = userService.getUser(userId);
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
}
