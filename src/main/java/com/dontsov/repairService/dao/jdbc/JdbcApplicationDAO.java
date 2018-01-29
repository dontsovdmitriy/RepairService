package com.dontsov.repairService.dao.jdbc;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import org.apache.log4j.Logger;

import com.dontsov.repairService.dao.*;
import com.dontsov.repairService.model.*;

public class JdbcApplicationDAO implements ApplicationDAO {

	private static final int PAG_LIMIT = 2;
	private static final Logger LOGGER = Logger.getLogger(JdbcApplicationDAO.class);

	private static final String INSERT_APPLICATION = "INSERT INTO application " + "(creation_date, malfunction_type, description, client_id, status, completion_date) " 
			+ "VALUES (?, ?, ?, ?, ?, ?)";
	private static final String SELECT_ALL_APPLICATION = "SELECT "
			+ "ap.id AS ap_id, "
			+ "ap.creation_date AS ap_creation_date, "
			+ "ap.description AS ap_description, "
			+ "ap.price AS ap_price, "
			+ "ap.status AS ap_status, "
			+ "ap.completion_date AS ap_completion_date, "
			+ "ap.service_comment AS ap_service_comment, "
			+ "cl.id AS cl_id, "
			+ "cl.name AS cl_name, "
			+ "cl.surname AS cl_surname, "
			+ "cl.second_name AS cl_second_name, "
			+ "mn.id AS mn_id, "
			+ "mn.name AS mn_name, "
			+ "mn.surname AS mn_surname, "
			+ "mn.second_name AS mn_second_name, "
			+ "ms.id AS ms_id, "
			+ "ms.name AS ms_name, "
			+ "ms.surname AS ms_surname, "
			+ "ms.second_name AS ms_second_name, "
			+ "m_t.id AS m_t_id, "
			+ "m_t.type AS m_t_type "
		+ "FROM repair_service.application AS ap "
		+ "LEFT JOIN repair_service.user AS cl ON (cl.id = ap.client_id) "
		+ "LEFT JOIN repair_service.user AS mn ON (mn.id = ap.manager_id) "
		+ "LEFT JOIN repair_service.user AS ms ON (ms.id = ap.master_id) "
		+ "LEFT JOIN repair_service.malfunction_type AS m_t ON (m_t.id = ap.malfunction_type)";
	private static final String SELECT_ALL_APPLICATION_PAG = "SELECT "
			+ "ap.id AS ap_id, "
			+ "ap.creation_date AS ap_creation_date, "
			+ "ap.description AS ap_description,"
			+ "ap.price AS ap_price, "
			+ "ap.status AS ap_status, "
			+ "ap.completion_date AS ap_completion_date, "
			+ "ap.service_comment AS ap_service_comment, "
			+ "cl.id AS cl_id, "
			+ "cl.name AS cl_name, "
			+ "cl.surname AS cl_surname, "
			+ "cl.second_name AS cl_second_name, "
			+ "mn.id AS mn_id, "
			+ "mn.name AS mn_name, "
			+ "mn.surname AS mn_surname, "
			+ "mn.second_name AS mn_second_name, "
			+ "ms.id AS ms_id, "
			+ "ms.name AS ms_name, "
			+ "ms.surname AS ms_surname, "
			+ "ms.second_name AS ms_second_name, "
			+ "m_t.id AS m_t_id, "
			+ "m_t.type AS m_t_type "
		+ "FROM repair_service.application AS ap "
		+ "LEFT JOIN repair_service.user AS cl ON (cl.id = ap.client_id) "
		+ "LEFT JOIN repair_service.user AS mn ON (mn.id = ap.manager_id) "
		+ "LEFT JOIN repair_service.user AS ms ON (ms.id = ap.master_id) "
		+ "LEFT JOIN repair_service.malfunction_type AS m_t ON (m_t.id = ap.malfunction_type) "
		+ "ORDER BY ap.id ASC LIMIT " + PAG_LIMIT + " OFFSET %d";
		private static final String SELECT_APPLICATION_BY_USER = "SELECT "
				+ "ap.id AS ap_id, "
				+ "ap.creation_date AS ap_creation_date, "
				+ "ap.description AS ap_description,"
				+ "ap.price AS ap_price, "
				+ "ap.status AS ap_status, "
				+ "ap.completion_date AS ap_completion_date, "
				+ "ap.service_comment AS ap_service_comment, "
				+ "cl.id AS cl_id, "
				+ "cl.name AS cl_name, "
				+ "cl.surname AS cl_surname, "
				+ "cl.second_name AS cl_second_name, "
				+ "mn.id AS mn_id, "
				+ "mn.name AS mn_name, "
				+ "mn.surname AS mn_surname, "
				+ "mn.second_name AS mn_second_name, "
				+ "ms.id AS ms_id, "
				+ "ms.name AS ms_name, "
				+ "ms.surname AS ms_surname, "
				+ "ms.second_name AS ms_second_name, "
				+ "m_t.id AS m_t_id, "
				+ "m_t.type AS m_t_type "
			+ "FROM repair_service.application AS ap "
			+ "LEFT JOIN repair_service.user AS cl ON (cl.id = ap.client_id) "
			+ "LEFT JOIN repair_service.user AS mn ON (mn.id = ap.manager_id) "
			+ "LEFT JOIN repair_service.user AS ms ON (ms.id = ap.master_id) "
			+ "LEFT JOIN repair_service.malfunction_type AS m_t ON (m_t.id = ap.malfunction_type) "
			+ "WHERE cl.id = ? "
			+ "ORDER BY ap.id ASC LIMIT " + PAG_LIMIT + " OFFSET %d";
	private static final String SELECT_APPLICATIONS_BY_STATUS = "SELECT "
			+ "ap.id AS ap_id, "
			+ "ap.creation_date AS ap_creation_date, "
			+ "ap.description AS ap_description,"
			+ "ap.price AS ap_price, "
			+ "ap.status AS ap_status, "
			+ "ap.completion_date AS ap_completion_date, "
			+ "ap.service_comment AS ap_service_comment, "
			+ "cl.id AS cl_id, "
			+ "cl.name AS cl_name, "
			+ "cl.surname AS cl_surname, "
			+ "cl.second_name AS cl_second_name, "
			+ "mn.id AS mn_id, "
			+ "mn.name AS mn_name, "
			+ "mn.surname AS mn_surname, "
			+ "mn.second_name AS mn_second_name, "
			+ "ms.id AS ms_id, "
			+ "ms.name AS ms_name, "
			+ "ms.surname AS ms_surname, "
			+ "ms.second_name AS ms_second_name, "
			+ "m_t.id AS m_t_id, "
			+ "m_t.type AS m_t_type "
		+ "FROM repair_service.application AS ap "
		+ "LEFT JOIN repair_service.user AS cl ON (cl.id = ap.client_id) "
		+ "LEFT JOIN repair_service.user AS mn ON (mn.id = ap.manager_id) "
		+ "LEFT JOIN repair_service.user AS ms ON (ms.id = ap.master_id) "
		+ "LEFT JOIN repair_service.malfunction_type AS m_t ON (m_t.id = ap.malfunction_type) "
		+ "WHERE ap.status = ?";
		private static final String SELECT_APPLICATIONS_BY_STATUS_AND_USER = "SELECT "
			+ "ap.id AS ap_id, "
			+ "ap.creation_date AS ap_creation_date, "
			+ "ap.description AS ap_description,"
			+ "ap.price AS ap_price, "
			+ "ap.status AS ap_status, "
			+ "ap.completion_date AS ap_completion_date, "
			+ "ap.service_comment AS ap_service_comment, "
			+ "cl.id AS cl_id, "
			+ "cl.name AS cl_name, "
			+ "cl.surname AS cl_surname, "
			+ "cl.second_name AS cl_second_name, "
			+ "mn.id AS mn_id, "
			+ "mn.name AS mn_name, "
			+ "mn.surname AS mn_surname, "
			+ "mn.second_name AS mn_second_name, "
			+ "ms.id AS ms_id, "
			+ "ms.name AS ms_name, "
			+ "ms.surname AS ms_surname, "
			+ "ms.second_name AS ms_second_name, "
			+ "m_t.id AS m_t_id, "
			+ "m_t.type AS m_t_type "
		+ "FROM repair_service.application AS ap "
		+ "LEFT JOIN repair_service.user AS cl ON (cl.id = ap.client_id) "
		+ "LEFT JOIN repair_service.user AS mn ON (mn.id = ap.manager_id) "
		+ "LEFT JOIN repair_service.user AS ms ON (ms.id = ap.master_id) "
		+ "LEFT JOIN repair_service.malfunction_type AS m_t ON (m_t.id = ap.malfunction_type) "
		+ "WHERE ap.status = ? AND ms.id = ?";
	private static final String DELETE_APPLICATION = "DELETE FROM application WHERE id = ?";
	private static final String UPDATE_APPLICATION_BY_MANAGER = "UPDATE application " +
			"SET manager_id=?, master_id=?, price=?, status=?, service_comment=?  WHERE id=?";
	private static final String UPDATE_APPLICATION_BY_MASTER = "UPDATE application " +
			"SET status=? WHERE id=?";
	private static final String SELECT_APPLICATION_AMMOUNT = "SELECT COUNT(*) FROM application";
	private static final String SELECT_APPLICATION_AMMOUNT_BY_CLIENT = "SELECT COUNT(*) FROM application WHERE client_id = ?";

	private Connection connection;

	JdbcApplicationDAO(Connection connection) {
		this.connection = connection;
	}

	public List<Application> getAll() {
		List<Application> resultList = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_APPLICATION)) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				resultList.add(UtilDAO.createApplication(rs));
			}

		} catch (SQLException e) {
			String message = String.format("Exception during find all application");
			LOGGER.error( message , e);
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
			ps.setDate(6, Date.valueOf(application.getCompletionDate()));

			ps.executeUpdate();

		} catch (SQLException e) {
			String message = String.format("Exception during add an application with client id = %s", application.getClient().getId());
			LOGGER.error( message , e);
			throw new RuntimeException(message, e);
		}
	}

	public Optional<Application> get(int id) {
		return null;
	}

	public void delete(int id) {
		try (PreparedStatement ps = connection.prepareStatement(DELETE_APPLICATION)){

			ps.setInt(1, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			String message = String.format("Exception during delete an application with id = %s", id);
			LOGGER.error( message , e);
			throw new RuntimeException(message, e);
		}
	}

	@Override
	public List<Application> getApplicationsByStatus(String applicationStatus) {
		List<Application> resultList = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(SELECT_APPLICATIONS_BY_STATUS)) {

			ps.setString(1, applicationStatus.toString());

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				resultList.add(UtilDAO.createApplication(rs));
			}

		} catch (SQLException e) {
			String message = String.format("Exception during find applications by status = " + applicationStatus);
			LOGGER.error( message , e);
			throw new RuntimeException(message, e);
		}
		return resultList;
	}

	@Override
	public void updateByManager(Application application) {
		try (PreparedStatement ps = connection.prepareStatement(UPDATE_APPLICATION_BY_MANAGER )){

			ps.setInt(1, application.getManager().getId());
			ps.setInt(2, application.getMaster().getId());
			ps.setLong(3, application.getPrice());
			ps.setString(4, application.getStatus().toString());
			ps.setString(5, application.getServiceComment());
			ps.setInt(6, application.getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			String message = String.format("Exception during update by manager a application with manager id = %s", application.getManager().getId());
			LOGGER.error( message , e);
			throw new RuntimeException(message, e);
		}		
	}

	@Override
	public void updateByMaster(Application application) {
		try (PreparedStatement ps = connection.prepareStatement(UPDATE_APPLICATION_BY_MASTER )){

			ps.setString(1, application.getStatus().toString().toUpperCase());
			ps.setInt(2, application.getId());
						
			ps.executeUpdate();

		} catch (SQLException e) {
			String message = String.format("Exception during update application with  id = %s", application.getId());
			LOGGER.error( message , e);
			throw new RuntimeException(message, e);
		}				
	}

	@Override
	public List<Application> getApplicationsByStatusAndUser(String applicationStatus, int id) {
		List<Application> resultList = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(SELECT_APPLICATIONS_BY_STATUS_AND_USER)) {

			ps.setString(1, applicationStatus.toString());
			ps.setInt(2, id);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				resultList.add(UtilDAO.createApplication(rs));
			}

		} catch (SQLException e) {
			String message = String.format("Exception during find all application for user with id=" + id + " with status =" + applicationStatus);
			LOGGER.error( message , e);
			throw new RuntimeException(message, e);
		}
		return resultList;
	}

	@Override
	public List<Application> getUserApplications(User user, int offset) {
		List<Application> resultList = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(String.format(SELECT_APPLICATION_BY_USER, offset))) {

			ps.setInt(1, user.getId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				resultList.add(UtilDAO.createApplication(rs));
			}

		} catch (SQLException e) {
			String message = String.format("Exception during find all applications for User with id=" + user.getId());
			LOGGER.error( message , e);
			throw new RuntimeException(message, e);
		}
		return resultList;
	}

	@Override
	public List<Application> getAllApplicationsPag(int offset) {
		List<Application> resultList = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(String.format(SELECT_ALL_APPLICATION_PAG, offset))) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				resultList.add(UtilDAO.createApplication(rs));
			}

		} catch (SQLException e) {
			String message = String.format("Exception during find all application with pagination offset = " + offset);
			LOGGER.error( message , e);
			throw new RuntimeException(message, e);
		}
		return resultList;
	}

	@Override
	public int getApplicationsAmmount() {
		int applicationsAmmount = 0;
		try (PreparedStatement ps = connection.prepareStatement(SELECT_APPLICATION_AMMOUNT)) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				applicationsAmmount = rs.getInt(1);
			}

		} catch (SQLException e) {
			String message = String.format("Exception during find ammount of all applications");
			LOGGER.error( message , e);
			throw new RuntimeException(message, e);
		}
		return applicationsAmmount;
	}

	@Override
	public int getApplicationsAmmountByUser(User user) {
		int applicationsAmmount = 0;
		try (PreparedStatement ps = connection.prepareStatement(SELECT_APPLICATION_AMMOUNT_BY_CLIENT)) {
			
			ps.setInt(1, user.getId());

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				applicationsAmmount = rs.getInt(1);
			}

		} catch (SQLException e) {
			String message = String.format("Exception during find ammount of all applications for user with id = " + user.getId());
			LOGGER.error( message , e);
			throw new RuntimeException(message, e);
		}
		return applicationsAmmount;
	}

}
