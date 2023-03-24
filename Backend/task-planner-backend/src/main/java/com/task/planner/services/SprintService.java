package com.task.planner.services;

import java.util.List;

import com.task.planner.dtos.SprintDTO;
import com.task.planner.dtos.TaskDTO;
import com.task.planner.exceptions.NoRecordFoundException;

public interface SprintService {

	/**
	 * 
	 * @param sprintDTO : Object of Sprint contains all information about Sprint.
	 * @return : SprintDTO object which has been inserted into database.
	 */
	public SprintDTO createSprint(SprintDTO sprintDTO);

	/**
	 * 
	 * @param sprintId : Integer Id to find the Sprint object in database.
	 * @return : SprintDTO object which we found in database.
	 * @throws NoRecordFoundException : If Sprint object does not found then
	 *                                NoRecordFoundException will be thrown at
	 *                                runtime.
	 */
	public SprintDTO getSprintById(Integer sprintId) throws NoRecordFoundException;

	/**
	 * 
	 * @param sprintId  : Integer Id to find the Sprint object in database.
	 * @param sprintDTO : Object of Sprint contains all information about Sprint
	 *                  which needed to update Sprint.
	 * @return : updated SprintDTO object.
	 * @throws NoRecordFoundException : If Sprint object does not found then
	 *                                NoRecordFoundException will be thrown at
	 *                                runtime.
	 */
	public SprintDTO updateSprint(Integer sprintId, SprintDTO sprintDTO) throws NoRecordFoundException;

	/**
	 * 
	 * @param sprintId : Integer Id to find the Sprint object in database.
	 * @return : String message which contains information about successful
	 *         operation.
	 * @throws NoRecordFoundException : If Sprint object does not found then
	 *                                NoRecordFoundException will be thrown at
	 *                                runtime.
	 */
	public String deleteSprint(Integer sprintId) throws NoRecordFoundException;

	/**
	 * 
	 * @return : Collection object of List<SprintDTO> will be returned.
	 * @throws NoRecordFoundException : It will throw NoRecordFoundException if list
	 *                                is empty.
	 */
	public List<SprintDTO> getAllSprints() throws NoRecordFoundException;

	/**
	 * 
	 * @param taskId   : To find the task in database.
	 * @param sprintId : To find the sprint inside database.
	 * @return : String message about task is inserted inside Collection of tasks.
	 * @throws NoRecordFoundException : If task or sprint is not found in database
	 *                                then NoRecordFoundException will be thrown.
	 */
	public String assignTaskToSprint(Integer taskId, Integer sprintId) throws NoRecordFoundException;

	/**
	 * 
	 * @param taskId   : To find the task in database.
	 * @param sprintId : To find the sprint inside database.
	 * @return : String message about task is removed from Collection of tasks.
	 * @throws NoRecordFoundException : If task or sprint is not found in database
	 *                                then NoRecordFoundException will be thrown.
	 */
	public String removeTaskFromSprint(Integer taskId, Integer sprintId) throws NoRecordFoundException;

	/**
	 * 
	 * @param sprintId : To find sprint with Id.
	 * @return : List<TaskDTO> which belongs to particular sprint.
	 * @throws NoRecordFoundException : If no any task found then will throw
	 *                                NoRecordFoundException.
	 */
	public List<TaskDTO> getAllTasksFromSprint(Integer sprintId) throws NoRecordFoundException;

}