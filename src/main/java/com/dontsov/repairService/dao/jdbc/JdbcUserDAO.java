package com.dontsov.repairService.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dontsov.repairService.dao.UserDAO;
import com.dontsov.repairService.dao.UtilDao;
import com.dontsov.repairService.model.MalfunctionType;
import com.dontsov.repairService.model.User;


public class JdbcUserDAO implements UserDAO {

	//TODO Logger 
	//TODO static variable
	private static final String INSERT_USER = "INSERT INTO user " + "(surname, name, second_name, email, phone, username, password, role) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String DELETE_USER = "DELETE FROM user WHERE id = ?";
	private static final String SELECT_ALL_USERS = "SELECT * FROM user";
	private static final String SELECT_FROM_USER_BY_ID = "SELECT * FROM user WHERE id = ?";


	private static final String IS_EMAIL_EXISTS = "SELECT COUNT(id) FROM user WHERE email = ?";
	private static final String IS_USERNAME_EXISTS = "SELECT COUNT(id) FROM user WHERE username = ?";
	private static final String SELECT_FROM_USER_BY_USERNAME = "SELECT * FROM user WHERE username = ?";

	private Connection connection;

	JdbcUserDAO(Connection connection) {
		this.connection = connection;
	}

	public List<User> getAll() {
		List<User> resultList = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_USERS)) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				resultList.add(UtilDao.createUser(rs));
			}

		} catch (SQLException e) {
			String message = String.format("Exception during find all Users");
		//	logger.error( message , e);
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
			//TODO hashpassword
			ps.setString(7, UtilDao.getPasswordHash(user.getPassword()));
			ps.setString(8, user.getRole().toString());

			ps.executeUpdate();

		} catch (SQLException e) {
			String message = String.format("Exception during add a user with username = %s", user.getUsername());
	//		logger.error( message , e);
			throw new RuntimeException(message, e);
		}
	}

	public Optional<User> get(int id) {
		Optional<User> result = Optional.empty();
		try (PreparedStatement ps = connection.prepareStatement(SELECT_FROM_USER_BY_ID)) {
			ps.setLong( 1 , id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				result = Optional.of(UtilDao.createUser(rs));
			}
		} catch (SQLException e) {
			String message = String.format("Exception during find  user with id= " + id);
	//		logger.error( message , e);
			throw new RuntimeException(message, e);
		}
		return result;
	}

	public void delete(int id) {
		try (PreparedStatement ps = connection.prepareStatement(DELETE_USER)){
			
			ps.setInt(1, id);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			String message = String.format("Exception during delete a user with id = %s", id);
		//	logger.error( message , e);
			throw new RuntimeException(message, e);
		}
	}

	@Override
	public boolean emailExistsInDb(String email) {
		try (PreparedStatement ps = connection.prepareStatement(IS_EMAIL_EXISTS, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, email);

			ResultSet rs = ps.executeQuery();
			return rs.next() && rs.getLong(1) > 0;

		} catch (SQLException e) {
			String message = String.format("Exception during find email = %s", email);
     //       logger.error( message , e);
			throw new RuntimeException(message, e);
		}
	}

	@Override
	public boolean usernameExistsInDb(String username) {
		try (PreparedStatement ps = connection.prepareStatement(IS_USERNAME_EXISTS, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, username);

			ResultSet rs = ps.executeQuery();
			return rs.next() && rs.getLong(1) > 0;

		} catch (SQLException e) {
			String message = String.format("Exception during find username = %s", username);
  //          logger.error( message , e);
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
				result = Optional.of(UtilDao.createUser(rs));
			}
		} catch (SQLException e) {
			String message = String.format("Exception during find  user with username= " + username);
    //        logger.error( message , e);
			throw new RuntimeException(message, e);
		}
		return result;
	}


}
