package com.task.planner.services;

import java.util.List;

import com.task.planner.dtos.TaskDTO;
import com.task.planner.dtos.UserDTO;
import com.task.planner.exceptions.NoRecordFoundException;

public interface UserService {

	/**
	 * 
	 * @param userDto : Object of user contains all details of User.
	 * @return : userDto that has been inserted into the database.
	 */
	public UserDTO createUser(UserDTO userDTO);

	/**
	 * 
	 * @param userId : userId by which we can find out user in database.
	 * @return : userDto that has been present in database.
	 * @throws NoRecordFoundException : If user not found then will throw
	 *                                NoRecordFoundException.
	 */
	public UserDTO getUserById(Integer userId) throws NoRecordFoundException;

	/**
	 * 
	 * @param userId  : userId by which we can find out user in database.
	 * @param userDto : userDto that have to be update in database.
	 * @return : userDto that has been updated in database.
	 * @throws NoRecordFoundException : If user not found then will throw
	 *                                NoRecordFoundException.
	 */
	public UserDTO updateUser(Integer userId, UserDTO userDTO) throws NoRecordFoundException;

	/**
	 * 
	 * @param userId : userId by which we can find out user in database.
	 * @return : String message which contain proper message about operation.
	 * @throws NoRecordFoundException : If user not found then will throw
	 *                                NoRecordFoundException.
	 */
	public String deleteUser(Integer userId) throws NoRecordFoundException;

	/**
	 * 
	 * @return : List<UserDTO> contains information about all users.
	 * @throws NoRecordFoundException : If List of user is empty then it will throw
	 *                                an NoRecordFoundException.
	 */
	public List<UserDTO> getAllUsers() throws NoRecordFoundException;

	/**
	 * 
	 * @param userId : To find user from database.
	 * @return : List<TaskDTO> which contains all information about task belongs to
	 *         particular userId.
	 * @throws NoRecordFoundException : If user don't have any task assigned then
	 *                                will throw NoRecordFoundException.
	 */
	public List<TaskDTO> getAllTaskByUserId(Integer userId) throws NoRecordFoundException;

	/**
	 * 
	 * @param userId : To find user from database.
	 * @param taskId : To find Task from database.
	 * @return : String message contains details of operation performed.
	 * @throws NoRecordFoundException : If Task or User does not found then throw
	 *                                NoRecordFoundException.
	 */
	public String assignTaskToUser(Integer userId, Integer taskId) throws NoRecordFoundException;

	/**
	 * 
	 * @param userId : To find user from database.
	 * @param taskId : To find Task from database.
	 * @return : String message contains details of operation performed.
	 * @throws NoRecordFoundException : If Task or User does not found then throw
	 *                                NoRecordFoundException.
	 */
	public String removeTaskFromUser(Integer userId, Integer taskId) throws NoRecordFoundException;

}
