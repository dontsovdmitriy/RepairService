package com.dontsov.repairService.dao;

import java.util.List;

import com.dontsov.repairService.model.Application;
import com.dontsov.repairService.model.User;

public interface ApplicationDAO extends GenericDAO<Application> {

	/**
	 * Return list of applications by application status
	 */
	List<Application> getApplicationsByStatus(String applicationStatus);

	/**
	 * Update application by user (manager)
	 */
	void updateByManager(Application application);

	/**
	 * Update application by user (master)
	 */
	void updateByMaster(Application application);

	/**
	 * Return list of applications by application status and user
	 */
	List<Application> getApplicationsByStatusAndUser(String applicationStatus, int id);

	/**
	 * Return list of applications by application status and pagination offset
	 */
	List<Application> getUserApplications(User user, int offset);
	
	/**
	 * Return list of applications by pagination offset
	 */
	List<Application> getAllApplicationsPag(int offset);

	/**
	 * Return applications amount
	 */
	int getApplicationsAmmount();

	/**
	 * Return applications amount by user
	 */
	int getApplicationsAmmountByUser(User user);

}
