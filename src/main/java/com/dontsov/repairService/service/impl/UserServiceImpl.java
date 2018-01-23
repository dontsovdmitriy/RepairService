package com.dontsov.repairService.service.impl;

import java.util.List;
import java.util.Optional;

import com.dontsov.repairService.dao.DaoConnection;
import com.dontsov.repairService.dao.DaoFactory;
import com.dontsov.repairService.dao.UserDAO;
import com.dontsov.repairService.dao.UtilDao;
import com.dontsov.repairService.model.User;
import com.dontsov.repairService.service.UserService;


public class UserServiceImpl implements UserService {

	private DaoFactory daoFactory;

	private static class Holder{
		static final UserService INSTANCE = new UserServiceImpl( DaoFactory.getInstance() ); 
	}

	private UserServiceImpl(DaoFactory instance) {
		this.daoFactory = instance;
	}

	public static UserService getInstance(){
		return Holder.INSTANCE;
	}
	
	public void saveUser(User user) {
		try(DaoConnection connection = daoFactory.getConnection()){
			UserDAO userDAO = daoFactory.createUserDAO(connection);
		//	logger.info("Searching invoice with subscription id = " + subscription.getId());
			userDAO.save(user);
		}
	}

	public void deleteUser(int id) {
		try(DaoConnection connection = daoFactory.getConnection()){
			UserDAO userDAO = daoFactory.createUserDAO(connection);
		//	logger.info("Searching invoice with subscription id = " + subscription.getId());
			userDAO.delete(id);
		}
	}

	public List<User> getUsers() {
		try(DaoConnection connection = daoFactory.getConnection()){
			UserDAO userDAO = daoFactory.createUserDAO(connection);
		//	logger.info("Searching invoice with subscription id = " + subscription.getId());
			return userDAO.getAll();
		}
	}

	public Optional<User> getUser(int id) {
		try(DaoConnection connection = daoFactory.getConnection()){
			UserDAO userDAO = daoFactory.createUserDAO(connection);
		//	logger.info("Searching invoice with subscription id = " + subscription.getId());
			return userDAO.get(id);
		}
	}

	public Optional<User> login(String username, String password) {
		try (DaoConnection connection = daoFactory.getConnection()){

			UserDAO userDao = daoFactory.createUserDAO(connection);
			
			Optional<User> user = userDao.findByUsername(username);
			
			if (!user.isPresent()) {
	//			logger.info("User with email = " + email + " didn't find in DB");
				return Optional.empty();
			}

			if (UtilDao.checkPassword(password, user.get().getPassword())) {
	//			logger.info("User with email = " + email + "password check is ok");
			return user;
			}
			return Optional.empty();
		}
	}

}
