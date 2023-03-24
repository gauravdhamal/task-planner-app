package com.task.planner.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.planner.dtos.SprintDTO;
import com.task.planner.entities.Sprint;
import com.task.planner.exceptions.NoRecordFoundException;
import com.task.planner.repositories.SprintRepository;

@Service
public class SprintServiceImpl implements SprintService {

	@Autowired
	private SprintRepository sprintRepository;

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
	public List<Sprint> getAllSprints() throws NoRecordFoundException {
		List<Sprint> sprints = sprintRepository.findAll();
		if (sprints.isEmpty()) {
			throw new NoRecordFoundException("No any sprint found in database.");
		} else {
			return sprints;
		}
	}

}
