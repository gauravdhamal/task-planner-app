package com.task.planner.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.planner.dtos.UserDTO;
import com.task.planner.entities.User;
import com.task.planner.exceptions.NoRecordFoundException;
import com.task.planner.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

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
	public UserDTO getUser(Integer userId) throws NoRecordFoundException {
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

}
