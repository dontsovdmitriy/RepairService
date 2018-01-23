package com.dontsov.repairService.service;

import java.util.List;
import java.util.Optional;

import com.dontsov.repairService.model.Application;

public interface ApplicationService {

	public List<Application> getApplications();

	public void saveApplication(Application application);

	public Optional<Application> getApplication(int id);

	public void deleteApplication(int theId);
}
