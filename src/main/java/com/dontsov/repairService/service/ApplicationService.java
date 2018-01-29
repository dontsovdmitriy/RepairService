package com.dontsov.repairService.service;

import java.util.List;
import java.util.Optional;

import com.dontsov.repairService.model.Application;
import com.dontsov.repairService.model.User;

public interface ApplicationService {

	public void saveApplication(Application application);

	public Optional<Application> getApplication(int id);

	public void deleteApplication(int theId);

	public List<Application> getApplicationsByStatus(String applicationStatus);

	public void updateByManager(Application application);

	public void updateByMaster(Application application);

	public List<Application> getApplicationsByStatusAndUser(String applicationStatus, int id);

	public List<Application> getUserApplications(User user, int offset);
	
	public List<Application> getAllApplicationsPag(int offset);

	public int getApplicationsAmmount();

	public List<Application> getApplications();

	public int getApplicationsAmmountByUser(User user);



}
