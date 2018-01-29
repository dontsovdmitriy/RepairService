package com.dontsov.repairService.dao;

import java.util.List;

import com.dontsov.repairService.model.Application;
import com.dontsov.repairService.model.User;

public interface ApplicationDAO extends GenericDAO<Application> {

	List<Application> getApplicationsByStatus(String applicationStatus);

	void updateByManager(Application application);

	void updateByMaster(Application application);

	List<Application> getApplicationsByStatusAndUser(String applicationStatus, int id);

	List<Application> getUserApplications(User user, int offset);
	
	List<Application> getAllApplicationsPag(int offset);

	int getApplicationsAmmount();

	int getApplicationsAmmountByUser(User user);

}
