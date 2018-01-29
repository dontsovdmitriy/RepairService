package com.dontsov.repairService.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.apache.log4j.Logger;

import com.dontsov.repairService.model.Application;
import com.dontsov.repairService.model.Application.ApplicationStatus;
import com.dontsov.repairService.model.MalfunctionType;
import com.dontsov.repairService.model.Review;
import com.dontsov.repairService.model.User;

public class UtilDAO {

	private static final Logger LOGGER = Logger.getLogger(UtilDAO.class);

	private UtilDAO() {
	}
	
	public static MalfunctionType createMalfunctionType(ResultSet resultSet)  {
		try {
			return new MalfunctionType.Builder()
					.setId(resultSet.getInt("id"))
					.setType(resultSet.getString("type"))
					.setRepairDay(resultSet.getInt("repair_day"))
					.build();
		} catch (SQLException e) {
			String message = String.format("Exception during create malfunctionType entity");
			LOGGER.error(message, e);
			throw new RuntimeException(message, e);
		}
	}
	
	public static User createUser(ResultSet resultSet)  {
		try {
			return new User.Builder()
					.setId(resultSet.getInt("id"))
					.setSurname(resultSet.getString("surname"))
					.setName(resultSet.getString("name"))
					.setSecondName(resultSet.getString("second_name"))
					.setEmail(resultSet.getString("email"))
					.setPhone(resultSet.getString("phone"))
					.setUsername(resultSet.getString("username"))
					.setPassword(resultSet.getString("password"))
					.setRole(User.Role.valueOf(resultSet.getString("role")))
					.build();

		} catch (SQLException e) {
			String message = String.format("Exception during create User entity");
			LOGGER.error(message, e);
			throw new RuntimeException(message, e);
		}
	}
	
	public static Review createReview(ResultSet resultSet)  {
		try {
						
			User client = new User.Builder()
					.setId(resultSet.getInt("cl_id"))
					.setSurname(resultSet.getString("cl_username"))
					.build();
			
			return new Review.Builder()
					.setId(resultSet.getInt("rv_id"))
					.setDescription(resultSet.getString("rv_description"))
					.setClient(client)
					.build();

		} catch (SQLException e) {
			String message = String.format("Exception during create Review entity");
			LOGGER.error(message, e);
			throw new RuntimeException(message, e);
		}
	}
	
	public static Application createApplication(ResultSet resultSet)  {
		try {
						
			MalfunctionType malfunctionType = new MalfunctionType.Builder()
					.setId(resultSet.getInt("m_t_id"))
					.setType(resultSet.getString("m_t_type"))
					.build();
								
			User client = new User.Builder()
					.setId(resultSet.getInt("cl_id"))
					.setSurname(resultSet.getString("cl_surname"))
					.setName(resultSet.getString("cl_name"))
					.setSecondName(resultSet.getString("cl_second_name"))
					.build();
			
			User manager = new User.Builder()
					.setId(resultSet.getInt("mn_id"))
					.setSurname(resultSet.getString("mn_surname"))
					.setName(resultSet.getString("mn_name"))
					.setSecondName(resultSet.getString("mn_second_name"))
					.build();
			
			User master = new User.Builder()
					.setId(resultSet.getInt("ms_id"))
					.setSurname(resultSet.getString("ms_surname"))
					.setName(resultSet.getString("ms_name"))
					.setSecondName(resultSet.getString("ms_second_name"))
					.build();
			
			return new Application.Builder()
					.setId(Integer.parseInt(resultSet.getString("ap_id")))
					.setCreationDate(LocalDate.parse(resultSet.getString("ap_creation_date")))
					.setMalfunctionType(malfunctionType)
					.setDescription(resultSet.getString("ap_description"))
					.setClient(client)
					.setManager(manager)
					.setMaster(master)
					.setPrice(resultSet.getLong("ap_price"))
					.setStatus(ApplicationStatus.valueOf(resultSet.getString("ap_status")))
					.setCompletionDate(LocalDate.parse(resultSet.getString("ap_completion_date")))
					.setServiceComment(resultSet.getString("ap_service_comment"))
					.build();
					
		} catch (SQLException e) {
			String message = String.format("Exception during create Application entity");
			LOGGER.error(message, e);
			throw new RuntimeException(message, e);
		}
	}
	
	public static String getPasswordHash(String password) {
		String result = null;
		try {
			MessageDigest ms = MessageDigest.getInstance("SHA-256");
			result = new String(ms.digest(password.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			String message = String.format("Exception during get password hash");
			LOGGER.error(message, e);
			throw new RuntimeException(message, e);
		}
		return result;
	}

	public static boolean checkPassword(String passwordInput, String storedHash) {
		boolean passwordCorrect = false;
		if (getPasswordHash(passwordInput).equals(storedHash)) {
			passwordCorrect = true;
		}
		return passwordCorrect;
	}

}
