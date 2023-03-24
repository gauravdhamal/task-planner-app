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

	@Override
	public UserDTO createUser(UserDTO userDto) {
		ModelMapper modelMapper = new ModelMapper();
		User user = modelMapper.map(userDto, User.class);
		user = userRepository.save(user);
		userDto = modelMapper.map(user, UserDTO.class);
		return userDto;
	}

	@Override
	public UserDTO getUser(Integer userId) throws NoRecordFoundException {
		return null;
	}

	@Override
	public UserDTO updateUser(Integer userId, UserDTO userDto) throws NoRecordFoundException {
		return null;
	}

	@Override
	public String deleteUser(Integer userId) throws NoRecordFoundException {
		return null;
	}

}
