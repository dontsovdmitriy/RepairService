package com.dontsov.repairService.service;

import java.util.List;
import java.util.Optional;

import com.dontsov.repairService.model.Application;
import com.dontsov.repairService.model.User;

/**
 * The {@code ApplicationService} interface provides methods for {@code Application} object.
 */
public interface ApplicationService {

	/**
     * Method add new application 
     */
	public void saveApplication(Application application);

	/**
     * Method get application entity by id
     */
	public Optional<Application> getApplication(int id);

	/**
     * Method delete application entity by id
     */
	public void deleteApplication(int theId);

	/**
     * Method return list of application by status
     */
	public List<Application> getApplicationsByStatus(String applicationStatus);

	/**
     * Method processing application by manager
     */
	public void updateByManager(Application application);

	/**
     * Method processing application by master
     */
	public void updateByMaster(Application application);

	/**
     * Method return application list by status and user id
     */
	public List<Application> getApplicationsByStatusAndUser(String applicationStatus, int id);

	/**
     * Method return application list by user id for pagination view
     */
	public List<Application> getUserApplications(User user, int offset);
	
	/**
     * Method return application list for pagination view
     */
	public List<Application> getAllApplicationsPag(int offset);

	/**
     * Method return application amount in DB
     */
	public int getApplicationsAmmount();

	/**
     * Method return all applications
     */
	public List<Application> getApplications();

	/**
     * Method return all applications by user
     */
	public int getApplicationsAmmountByUser(User user);



}
