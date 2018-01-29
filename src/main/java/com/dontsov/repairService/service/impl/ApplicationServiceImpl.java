package com.dontsov.repairService.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.dontsov.repairService.dao.*;
import com.dontsov.repairService.model.*;
import com.dontsov.repairService.service.*;

public class ApplicationServiceImpl implements ApplicationService {
	
	private static final Logger LOGGER = Logger.getLogger(ApplicationServiceImpl.class);

	private DaoFactory daoFactory;

	private static class Holder{
		static final ApplicationService INSTANCE = new ApplicationServiceImpl( DaoFactory.getInstance() ); 
	}

	private ApplicationServiceImpl(DaoFactory instance) {
		this.daoFactory = instance;
	}

	public static ApplicationService getInstance(){
		return Holder.INSTANCE;
	}

	@Override
	public void saveApplication(Application application) {
		try(DaoConnection connection = daoFactory.getConnection()){
			
			ApplicationDAO applicationDAO = daoFactory.createApplicationDAO(connection);
			
			LOGGER.info("Save application by client id = " + application.getClient().getId());
			
			applicationDAO.save(application);
		}

	}

	@Override
	public Optional<Application> getApplication(int id) {
		try(DaoConnection connection = daoFactory.getConnection()){
			
			ApplicationDAO applicationDAO = daoFactory.createApplicationDAO(connection);
			
			LOGGER.info("Searching application with id = " + id);
			
			return applicationDAO.get(id);
		}
	}

	@Override
	public void deleteApplication(int id) {
		try(DaoConnection connection = daoFactory.getConnection()){
			
			ApplicationDAO applicationDAO = daoFactory.createApplicationDAO(connection);
			
			LOGGER.info("Delete application with id = " + id);
			
			applicationDAO.delete(id);
		}
	}

	@Override
	public List<Application> getApplicationsByStatus(String applicationStatus) {
		try(DaoConnection connection = daoFactory.getConnection()){
			
			ApplicationDAO applicationDAO = daoFactory.createApplicationDAO(connection);

			LOGGER.info("Getting applications with status = " + applicationStatus);

			List<Application> applications = applicationDAO.getApplicationsByStatus(applicationStatus);

			return applications;

		}
	}

	@Override
	public void updateByManager(Application application) {
		try(DaoConnection connection = daoFactory.getConnection()){
			
			ApplicationDAO applicationDAO = daoFactory.createApplicationDAO(connection);
			
			LOGGER.info("Update application with id = " + application.getId() + " by manager");
			
			applicationDAO.updateByManager(application);
		}

	}

	@Override
	public void updateByMaster(Application application) {
		try(DaoConnection connection = daoFactory.getConnection()){
			
			ApplicationDAO applicationDAO = daoFactory.createApplicationDAO(connection);
			
			LOGGER.info("Update application with id = " + application.getId() + " by master");
			
			applicationDAO.updateByMaster(application);
		}		
	}

	@Override
	public List<Application> getApplicationsByStatusAndUser(String applicationStatus, int id) {
		try(DaoConnection connection = daoFactory.getConnection()){
			
			ApplicationDAO applicationDAO = daoFactory.createApplicationDAO(connection);
			
			LOGGER.info("Getting applications by status = " + applicationStatus + " and user with id = " + id);
			
			List<Application> applications = applicationDAO.getApplicationsByStatusAndUser(applicationStatus, id);

			return applications;
		}

	}

	@Override
	public List<Application> getUserApplications(User user, int offset) {
		try(DaoConnection connection = daoFactory.getConnection()){
			
			ApplicationDAO applicationDAO = daoFactory.createApplicationDAO(connection);
			
			LOGGER.info("Get applications for user with id = " + user.getId());
			
			return applicationDAO.getUserApplications(user,offset);
		}
	}

	@Override
	public List<Application> getAllApplicationsPag(int offset) {
		try(DaoConnection connection = daoFactory.getConnection()){
			
			ApplicationDAO applicationDAO = daoFactory.createApplicationDAO(connection);
			
			LOGGER.info("Get all applications with paggination");
			
			return applicationDAO.getAllApplicationsPag(offset);
		}
	}

	@Override
	public int getApplicationsAmmount() {
		try(DaoConnection connection = daoFactory.getConnection()){
			
			ApplicationDAO applicationDAO = daoFactory.createApplicationDAO(connection);
			
			LOGGER.info("Get ammount of applications");
			
			return applicationDAO.getApplicationsAmmount();
		}
	}

	@Override
	public List<Application> getApplications() {
		try(DaoConnection connection = daoFactory.getConnection()){
			
			ApplicationDAO applicationDAO = daoFactory.createApplicationDAO(connection);
			
			LOGGER.info("Get all applications");
			
			return applicationDAO.getAll();
		}
	}

	@Override
	public int getApplicationsAmmountByUser(User user) {
		try(DaoConnection connection = daoFactory.getConnection()){
			
			ApplicationDAO applicationDAO = daoFactory.createApplicationDAO(connection);
			
			LOGGER.info("Get ammount of applications for user with id= " + user.getId());
			
			return applicationDAO.getApplicationsAmmountByUser(user);
		}	}
}
