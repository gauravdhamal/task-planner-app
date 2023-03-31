package com.task.planner.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.task.planner.dtos.TaskDTO;
import com.task.planner.dtos.UserDTO;
import com.task.planner.entities.Task;
import com.task.planner.entities.User;
import com.task.planner.enums.Gender;
import com.task.planner.exceptions.NoRecordFoundException;
import com.task.planner.repositories.TaskRepository;
import com.task.planner.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
		user = userRepository.save(user);
		userDTO = modelMapper.map(user, UserDTO.class);
		return userDTO;
	}

	@Override
	public UserDTO getUserById(Integer userId) throws NoRecordFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new NoRecordFoundException("User not found with Id : " + userId));
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		return userDTO;
	}

	@Override
	public UserDTO updateUser(Integer userId, UserDTO userDTO) throws NoRecordFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new NoRecordFoundException("User not found with Id : " + userId));
		if (userDTO.getName() != null) {
			user.setName(userDTO.getName());
		}
		if (userDTO.getUsername() != null) {
			user.setUsername(userDTO.getUsername());
		}
		if (userDTO.getPassword() != null) {
			user.setPassword(userDTO.getPassword());
		}
		user = userRepository.save(user);
		userDTO = modelMapper.map(user, UserDTO.class);
		return userDTO;
	}

	@Override
	public String deleteUser(Integer userId) throws NoRecordFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new NoRecordFoundException("User not found with Id : " + userId));
		userRepository.delete(user);
		return "User removed from database with Id : " + userId;
	}

	@Override
	public List<UserDTO> getAllUsers() throws NoRecordFoundException {
		List<User> users = userRepository.findAll();
		if (users.isEmpty()) {
			throw new NoRecordFoundException("No any user found in database.");
		} else {
			List<UserDTO> userDTOs = new ArrayList<>();
			for (User user : users) {
				userDTOs.add(modelMapper.map(user, UserDTO.class));
			}
			return userDTOs;
		}
	}

	@Override
	public List<User> getAllUsersSortByGender(Gender gender) throws NoRecordFoundException {
		Sort sort = Sort.by(gender == Gender.MALE ? Direction.DESC : Direction.ASC, "gender");
		List<User> users = userRepository.findAll(sort);
		if (users.isEmpty())
			throw new NoRecordFoundException("No any user found.");
		return users;
	}

	@Override
	public List<TaskDTO> getAllTaskByUserId(Integer userId) throws NoRecordFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new NoRecordFoundException("User not found with Id : " + userId));
		List<Task> tasks = taskRepository.findByUserId(user.getUserId());
		if (tasks.isEmpty()) {
			throw new NoRecordFoundException("User don't have any task assigned to them.");
		} else {
			List<TaskDTO> taskDTOs = new ArrayList<>();
			for (Task task : tasks) {
				taskDTOs.add(modelMapper.map(task, TaskDTO.class));
			}
			return taskDTOs;
		}
	}

	@Override
	public String assignTaskToUser(Integer userId, Integer taskId) throws NoRecordFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new NoRecordFoundException("User not found with Id : " + userId));
		Task task = taskRepository.findById(taskId)
				.orElseThrow(() -> new NoRecordFoundException("Task not found with Id : " + taskId));
		if (task.getUser() != null) {
			throw new NoRecordFoundException(
					"This task already belongs to another user with userId : " + user.getUserId());
		} else {
			task.setUser(user);
			user.getTasks().add(task);
			taskRepository.save(task);
			userRepository.save(user);
			return "Task with taskId : " + taskId + ". Assigned to user with userId " + userId;
		}
	}

	@Override
	public String removeTaskFromUser(Integer userId, Integer taskId) throws NoRecordFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new NoRecordFoundException("User not found with Id : " + userId));
		Task oldTask = taskRepository.findById(taskId)
				.orElseThrow(() -> new NoRecordFoundException("Task not found with Id : " + taskId));
		if (oldTask.getUser() == null) {
			throw new NoRecordFoundException("This task does not belong to any user.");
		} else if (oldTask.getUser().getUserId() != userId) {
			throw new NoRecordFoundException("This task does not belong user with userId : " + userId);
		} else {
			oldTask.setUser(null);
			user.getTasks().removeIf(task -> task.getTaskId() == taskId);
			taskRepository.save(oldTask);
			userRepository.save(user);
			return "Task with taskId " + taskId + ". Removed from user with userId " + userId;
		}
	}

	@Override
	public List<User> getAllUsersSortByName(String value) throws NoRecordFoundException {
		value = value.toUpperCase();
		Sort sort = Sort.by(value.equals("ASC") ? Direction.ASC : Direction.DESC, "name");
		List<User> users = userRepository.findAll(sort);
		if (users.isEmpty())
			throw new NoRecordFoundException("No any user found in database.");
		return users;
	}

}
