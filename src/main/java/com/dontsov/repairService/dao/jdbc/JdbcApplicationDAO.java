package com.dontsov.repairService.dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.dontsov.repairService.dao.ApplicationDAO;
import com.dontsov.repairService.dao.UtilDao;
import com.dontsov.repairService.model.Application;
import com.dontsov.repairService.model.MalfunctionType;

public class JdbcApplicationDAO implements ApplicationDAO {

	//TODO Logger 
	//TODO static variable

	private static final Logger logger = Logger.getLogger(JdbcApplicationDAO.class);


	private static final String INSERT_APPLICATION = "INSERT INTO application " + "(creation_date, malfunction_type, description, client_id, status) " 
			+ "VALUES (?, ?, ?, ?, ?)";
	//	private static final String IS_PUBLISHER_EXISTS = "SELECT COUNT(id) FROM publishers WHERE publisher = ?";
	private static final String SELECT_ALL_APPLICATION = "SELECT * FROM application";
	private static final String DELETE_APPLICATION = "DELETE FROM application WHERE id = ?";

	private Connection connection;

	JdbcApplicationDAO(Connection connection) {
		this.connection = connection;
	}

	public List<Application> getAll() {
		List<Application> resultList = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_APPLICATION)) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				resultList.add(UtilDao.createApplication(rs));
			}

		} catch (SQLException e) {
			String message = String.format("Exception during find all application");
			logger.error( message , e);
			throw new RuntimeException(message, e);
		}
		return resultList;
	}

	public void save(Application application) {
		try (PreparedStatement ps = connection.prepareStatement(INSERT_APPLICATION )){

			ps.setDate(1, Date.valueOf(application.getCreationDate()));
			ps.setInt(2, application.getMalfunctionType().getId());
			ps.setString(3, application.getDescription());
			ps.setInt(4, application.getClient().getId());
			ps.setString(5, application.getStatus().toString());

			ps.executeUpdate();

		} catch (SQLException e) {
			String message = String.format("Exception during add a application with client id = %s", application.getClient().getId());
			logger.error( message , e);
			throw new RuntimeException(message, e);
		}
	}

	public Optional<Application> get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(int id) {
		try (PreparedStatement ps = connection.prepareStatement(DELETE_APPLICATION)){

			ps.setInt(1, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			String message = String.format("Exception during delete a application with id = %s", id);
			//	logger.error( message , e);
			throw new RuntimeException(message, e);
		}
	}

}
