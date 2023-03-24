package com.task.planner.services;

import com.task.planner.dtos.UserDTO;
import com.task.planner.exceptions.NoRecordFoundException;

public interface UserService {

	/**
	 * 
	 * @param userDto : Object of user contains all details of User.
	 * @return : userDto that has been inserted into the database.
	 */
	public UserDTO createUser(UserDTO userDto);

	/**
	 * 
	 * @param userId : userId by which we can find out user in database.
	 * @return : userDto that has been present in database.
	 * @throws NoRecordFoundException : If user not found then will throw
	 *                                NoRecordFoundException.
	 */
	public UserDTO getUser(Integer userId) throws NoRecordFoundException;

	/**
	 * 
	 * @param userId  : userId by which we can find out user in database.
	 * @param userDto : userDto that have to be update in database.
	 * @return : userDto that has been updated in database.
	 * @throws NoRecordFoundException : If user not found then will throw
	 *                                NoRecordFoundException.
	 */
	public UserDTO updateUser(Integer userId, UserDTO userDto) throws NoRecordFoundException;

	/**
	 * 
	 * @param userId : userId by which we can find out user in database.
	 * @return : String message which contain proper message about operation.
	 * @throws NoRecordFoundException : If user not found then will throw
	 *                                NoRecordFoundException.
	 */
	public String deleteUser(Integer userId) throws NoRecordFoundException;

}
