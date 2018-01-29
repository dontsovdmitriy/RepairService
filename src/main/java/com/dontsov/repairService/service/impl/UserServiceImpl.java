package com.dontsov.repairService.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.dontsov.repairService.dao.*;
import com.dontsov.repairService.model.User;
import com.dontsov.repairService.model.User.Role;
import com.dontsov.repairService.service.UserService;


public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

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
	
	@Override
	public void saveUser(User user) {
		try(DaoConnection connection = daoFactory.getConnection()){
			
			UserDAO userDAO = daoFactory.createUserDAO(connection);
			
			LOGGER.info("Save user with username = " + user.getUsername());
			
			userDAO.save(user);
		}
	}

	@Override
	public void deleteUser(int id) {
		try(DaoConnection connection = daoFactory.getConnection()){
			
			UserDAO userDAO = daoFactory.createUserDAO(connection);
			
			LOGGER.info("Delete user with id = " + id);
			
			userDAO.delete(id);
		}
	}

	@Override
	public List<User> getUsers() {
		try(DaoConnection connection = daoFactory.getConnection()){
			
			UserDAO userDAO = daoFactory.createUserDAO(connection);
			
			LOGGER.info("Get all users ");
			
			return userDAO.getAll();
		}
	}

	@Override
	public Optional<User> getUser(int id) {
		try(DaoConnection connection = daoFactory.getConnection()){
			
			UserDAO userDAO = daoFactory.createUserDAO(connection);
			
			LOGGER.info("Getting user with id = " + id);
			
			return userDAO.get(id);
		}
	}

	@Override
	public Optional<User> login(String username, String password) {
		try (DaoConnection connection = daoFactory.getConnection()){

			UserDAO userDao = daoFactory.createUserDAO(connection);
			
			Optional<User> user = userDao.findByUsername(username);
			
			if (!user.isPresent()) {
				LOGGER.info("User with email = " + username + " didn't find in DB");
				return Optional.empty();
			}

			if (UtilDAO.checkPassword(password, user.get().getPassword())) {
				LOGGER.info("User with email = " + username + "password check is ok");
			return user;
			}
			
			return Optional.empty();
		}
	}

	@Override
	public void changeRole(User user) {
		try(DaoConnection connection = daoFactory.getConnection()){
			
			UserDAO userDAO = daoFactory.createUserDAO(connection);
			
			LOGGER.info("Change role for user with id = " + user.getId());
			
			userDAO.changeRole(user);
		}
		
	}

	@Override
	public List<User> getUserByRole(Role role) {
		try(DaoConnection connection = daoFactory.getConnection()){
			
			UserDAO userDAO = daoFactory.createUserDAO(connection);
			
			LOGGER.info("Get users by role = " + role);
			
			return userDAO.getUserByRole(role);
		}
	}

	@Override
	public boolean emailExistInDB(String email) {
		try(DaoConnection connection = daoFactory.getConnection()){
			
			UserDAO userDAO = daoFactory.createUserDAO(connection);
			
			LOGGER.info("Check email = " + email + " existing in DB" );
			
			return userDAO.emailExistInDB(email);
		}
	}

	@Override
	public boolean usernameExistInDB(String username) {
		try(DaoConnection connection = daoFactory.getConnection()){
			
			UserDAO userDAO = daoFactory.createUserDAO(connection);
			
			LOGGER.info("Check username = " + username + " existing in DB" );
			
			return userDAO.usernameExistInDB(username);
		}
	}

	@Override
	public List<User> getUsersPag(int offset) {
		try(DaoConnection connection = daoFactory.getConnection()){
			
			UserDAO userDAO = daoFactory.createUserDAO(connection);
			
			LOGGER.info("Get all users with paggination");
			
			return userDAO.getUsersPag(offset);
		}
	}

	@Override
	public int getUsersAmmount() {
		try(DaoConnection connection = daoFactory.getConnection()){
			
			UserDAO userDAO = daoFactory.createUserDAO(connection);
			
			LOGGER.info("Getting ammount of users");
			
			return userDAO.getUsersAmmount();
		}	}

	



}
