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

import com.task.planner.dtos.SprintDTO;
import com.task.planner.dtos.TaskDTO;
import com.task.planner.entities.Sprint;
import com.task.planner.exceptions.NoRecordFoundException;
import com.task.planner.services.SprintService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/sprints")
@CrossOrigin(origins = "*")
public class SprintController {

	@Autowired
	private SprintService sprintService;

	@PostMapping("/create")
	public ResponseEntity<SprintDTO> createSprint(@RequestBody @Valid SprintDTO sprintDTO) {
		sprintDTO = sprintService.createSprint(sprintDTO);
		return new ResponseEntity<SprintDTO>(sprintDTO, HttpStatus.ACCEPTED);
	}

	@GetMapping("/get/{sprintId}")
	public ResponseEntity<SprintDTO> getSprintById(@PathVariable("sprintId") Integer sprintId)
			throws NoRecordFoundException {
		SprintDTO sprintDTO = sprintService.getSprintById(sprintId);
		return new ResponseEntity<SprintDTO>(sprintDTO, HttpStatus.ACCEPTED);
	}

	@PutMapping("/update/{sprintId}")
	public ResponseEntity<SprintDTO> updateSprint(@PathVariable("sprintId") Integer sprintId,
			@RequestBody @Valid SprintDTO sprintDTO) throws NoRecordFoundException {
		sprintDTO = sprintService.updateSprint(sprintId, sprintDTO);
		return new ResponseEntity<SprintDTO>(sprintDTO, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{sprintId}")
	public ResponseEntity<String> deleteSprint(@PathVariable("sprintId") Integer sprintId)
			throws NoRecordFoundException {
		String result = sprintService.deleteSprint(sprintId);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Sprint>> getAllSprints() throws NoRecordFoundException {
		List<Sprint> sprints = sprintService.getAllSprints();
		return new ResponseEntity<List<Sprint>>(sprints, HttpStatus.OK);
	}

	@PostMapping("/assignTaskToSprint/{taskId}/{sprintId}")
	public ResponseEntity<String> assignTaskToSprint(@PathVariable("taskId") Integer taskId,
			@PathVariable("sprintId") Integer sprintId) throws NoRecordFoundException {
		String message = sprintService.assignTaskToSprint(taskId, sprintId);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@PostMapping("/removeTaskFromSprint/{taskId}/{sprintId}")
	public ResponseEntity<String> removeTaskFromSprint(@PathVariable("taskId") Integer taskId,
			@PathVariable("sprintId") Integer sprintId) throws NoRecordFoundException {
		String message = sprintService.removeTaskFromSprint(taskId, sprintId);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@GetMapping("/tasks/{sprintId}")
	public ResponseEntity<List<TaskDTO>> getAllTasksFromSprint(@PathVariable("sprintId") Integer sprintId)
			throws NoRecordFoundException {
		List<TaskDTO> taskDTOs = sprintService.getAllTasksFromSprint(sprintId);
		return new ResponseEntity<List<TaskDTO>>(taskDTOs, HttpStatus.OK);
	}

}
