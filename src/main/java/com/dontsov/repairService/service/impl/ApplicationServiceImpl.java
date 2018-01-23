package com.dontsov.repairService.service.impl;

import java.util.List;
import java.util.Optional;

import com.dontsov.repairService.dao.ApplicationDAO;
import com.dontsov.repairService.dao.DaoConnection;
import com.dontsov.repairService.dao.DaoFactory;
import com.dontsov.repairService.dao.MalfunctionTypeDAO;
import com.dontsov.repairService.model.Application;
import com.dontsov.repairService.model.MalfunctionType;
import com.dontsov.repairService.model.User;
import com.dontsov.repairService.service.ApplicationService;
import com.dontsov.repairService.service.MalfunctionTypeService;
import com.dontsov.repairService.service.UserService;


public class ApplicationServiceImpl implements ApplicationService {
	
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


	public List<Application> getApplications() {
		try(DaoConnection connection = daoFactory.getConnection()){
			ApplicationDAO applicationDAO = daoFactory.createApplicationDAO(connection);
			
			MalfunctionTypeService malfunctionTypeService = MalfunctionTypeServiceImpl.getInstance();
			UserService userService = UserServiceImpl.getInstance();


		//	logger.info("Searching invoice with subscription id = " + subscription.getId());
			
			List<Application> applications = applicationDAO.getAll();
			
			for (Application application : applications) {
				
				Optional<User> client = userService.getUser(application.getClient().getId());
				if (client.isPresent()) {
					application.setClient(client.get());
				}
				
				Optional<User> manager = userService.getUser(application.getManager().getId());
				if (manager.isPresent()) {
					application.setManager(manager.get());
				}
				
				Optional<User> master = userService.getUser(application.getMaster().getId());
				if (master.isPresent()) {
					application.setMaster(master.get());
				}
				
				Optional<MalfunctionType> malfunctionType = malfunctionTypeService.getMalfunctionType(application.getMalfunctionType().getId());
				if (malfunctionType.isPresent()) {
					application.setMalfunctionType(malfunctionType.get());
				}
			}
			return applications;
		}
	}

	public void saveApplication(Application application) {
		try(DaoConnection connection = daoFactory.getConnection()){
			ApplicationDAO applicationDAO = daoFactory.createApplicationDAO(connection);
		//	logger.info("Searching invoice with subscription id = " + subscription.getId());
			applicationDAO.save(application);
		}

	}

	public Optional<Application> getApplication(int id) {
		try(DaoConnection connection = daoFactory.getConnection()){
			ApplicationDAO applicationDAO = daoFactory.createApplicationDAO(connection);
		//	logger.info("Searching invoice with subscription id = " + subscription.getId());
			return applicationDAO.get(id);
		}
	}

	public void deleteApplication(int id) {
		try(DaoConnection connection = daoFactory.getConnection()){
			ApplicationDAO applicationDAO = daoFactory.createApplicationDAO(connection);
		//	logger.info("Searching invoice with subscription id = " + subscription.getId());
			applicationDAO.delete(id);
		}
	}

}
