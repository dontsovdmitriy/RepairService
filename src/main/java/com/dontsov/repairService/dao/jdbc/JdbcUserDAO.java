package com.dontsov.repairService.dao.jdbc;

import java.sql.*;
import java.util.*;

import org.apache.log4j.Logger;

import com.dontsov.repairService.dao.*;
import com.dontsov.repairService.model.User;
import com.dontsov.repairService.model.User.Role;


public class JdbcUserDAO implements UserDAO {

	private static final int PAG_LIMIT = 2;

	private static final String INSERT_USER = "INSERT INTO user " + "(surname, name, second_name, email, phone, username, password, role) " 
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String DELETE_USER = "DELETE FROM user WHERE id = ?";
	private static final String SELECT_ALL_USERS = "SELECT * FROM user";	
	private static final String SELECT_ALL_USERS_PAG = "SELECT * FROM user ORDER BY user.id ASC LIMIT " + PAG_LIMIT + " OFFSET %d";
	private static final String SELECT_FROM_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
	private static final String IS_EMAIL_EXISTS = "SELECT COUNT(id) FROM user WHERE email = ?";
	private static final String IS_USERNAME_EXISTS = "SELECT COUNT(id) FROM user WHERE username = ?";
	private static final String SELECT_FROM_USER_BY_USERNAME = "SELECT * FROM user WHERE username = ?";
	private static final String UPDATE_USER_ROLE_BY_ID = "UPDATE user SET role=? WHERE id=?";
	private static final String SELECT_USERS_BY_ROLE = "SELECT * FROM user WHERE role = ? ";
	private static final String SELECT_USERS_AMMOUNT = "SELECT COUNT(*) FROM user";
	
	private static final Logger LOGGER = Logger.getLogger(JdbcUserDAO.class);

	private Connection connection;

	JdbcUserDAO(Connection connection) {
		this.connection = connection;
	}

	public List<User> getAll() {
		List<User> resultList = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_USERS)) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				resultList.add(UtilDAO.createUser(rs));
			}

		} catch (SQLException e) {
			String message = String.format("Exception during find all Users");
			LOGGER.error( message , e);
			throw new RuntimeException(message, e);
		}
		return resultList;
	}

	public void save(User user) {
		try (PreparedStatement ps = connection.prepareStatement(INSERT_USER )){

			ps.setString(1, user.getSurname());
			ps.setString(2, user.getName());
			ps.setString(3, user.getSecondName());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getPhone());
			ps.setString(6, user.getUsername());
			ps.setString(7, UtilDAO.getPasswordHash(user.getPassword()));
			ps.setString(8, user.getRole().toString());

			ps.executeUpdate();

		} catch (SQLException e) {
			String message = String.format("Exception during add a user with username = %s", user.getUsername());
			LOGGER.error( message , e);
			throw new RuntimeException(message, e);
		}
	}

	public Optional<User> get(int id) {
		Optional<User> result = Optional.empty();
		try (PreparedStatement ps = connection.prepareStatement(SELECT_FROM_USER_BY_ID)) {
			
			ps.setLong( 1 , id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				result = Optional.of(UtilDAO.createUser(rs));
			}
			
		} catch (SQLException e) {
			String message = String.format("Exception during find  user with id= " + id);
			LOGGER.error( message , e);
			throw new RuntimeException(message, e);
		}
		return result;
	}

	public void delete(int id) {
		try (PreparedStatement ps = connection.prepareStatement(DELETE_USER)){
			
			ps.setInt(1, id);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			String message = String.format("Exception during delete user with id = %s", id);
			LOGGER.error( message , e);
			throw new RuntimeException(message, e);
		}
	}

	@Override
	public boolean emailExistInDB(String email) {
		try (PreparedStatement ps = connection.prepareStatement(IS_EMAIL_EXISTS, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();
			return rs.next() && rs.getLong(1) > 0;

		} catch (SQLException e) {
			String message = String.format("Exception during find email = %s", email);
			LOGGER.error( message , e);
			throw new RuntimeException(message, e);
		}
	}

	@Override
	public boolean usernameExistInDB(String username) {
		try (PreparedStatement ps = connection.prepareStatement(IS_USERNAME_EXISTS, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();
			return rs.next() && rs.getLong(1) > 0;

		} catch (SQLException e) {
			String message = String.format("Exception during find username = %s", username);
			LOGGER.error( message , e);
			throw new RuntimeException(message, e);
		}
	}

	@Override
	public Optional<User> findByUsername(String username) {
		
		Optional<User> result = Optional.empty();
		
		try (PreparedStatement ps = connection.prepareStatement(SELECT_FROM_USER_BY_USERNAME)) {
			
			ps.setString(1 , username);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = Optional.of(UtilDAO.createUser(rs));
			}
			
		} catch (SQLException e) {
			String message = String.format("Exception during find  user with username= " + username);
			LOGGER.error( message , e);
			throw new RuntimeException(message, e);
		}
		return result;
	}

	@Override
	public void changeRole(User user) {
		try (PreparedStatement ps = connection.prepareStatement(UPDATE_USER_ROLE_BY_ID )){

			ps.setString(1, user.getRole().toString());
			ps.setInt(2, user.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			String message = String.format("Exception during change role for user with id = %s", user.getId());
			LOGGER.error( message , e);
			throw new RuntimeException(message, e);
		}		
	}

	@Override
	public List<User> getUserByRole(Role role) {
		
		List<User> resultList = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(SELECT_USERS_BY_ROLE)) {

			ps.setString(1, role.toString());

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				resultList.add(UtilDAO.createUser(rs));
			}

		} catch (SQLException e) {
			String message = String.format("Exception during find all users with role= " + role.toString());
			LOGGER.error( message , e);
			throw new RuntimeException(message, e);
		}
		return resultList;
	}

	@Override
	public List<User> getUsersPag(int offset) {
		List<User> resultList = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(String.format(SELECT_ALL_USERS_PAG, offset))) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				resultList.add(UtilDAO.createUser(rs));
			}

		} catch (SQLException e) {
			String message = String.format("Exception during find all users with offset = " + offset);
			LOGGER.error( message , e);
			throw new RuntimeException(message, e);
		}
		return resultList;
	}

	@Override
	public int getUsersAmmount() {
		int usersAmmount = 0;
		try (PreparedStatement ps = connection.prepareStatement(SELECT_USERS_AMMOUNT)) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				usersAmmount = rs.getInt(1);
			}

		} catch (SQLException e) {
			String message = String.format("Exception during get ammount of all users");
			LOGGER.error( message , e);
			throw new RuntimeException(message, e);
		}
		return usersAmmount;
	}
}
