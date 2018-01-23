package com.dontsov.repairService.dao;

import java.security.*;
import java.sql.*;
import java.time.LocalDate;

import org.apache.log4j.Logger;

import com.dontsov.repairService.model.Application;
import com.dontsov.repairService.model.Application.ApplicationStatus;
import com.dontsov.repairService.model.MalfunctionType;
import com.dontsov.repairService.model.Review;
import com.dontsov.repairService.model.User;



public final class UtilDao {

	private static final Logger logger = Logger.getLogger(UtilDao.class);

	private UtilDao() {
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
			logger.error(message, e);
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
			logger.error(message, e);
			throw new RuntimeException(message, e);
		}
	}
	
	public static Review createReview(ResultSet resultSet)  {
		try {
						
			User.Builder userBuilder = new User.Builder();
			userBuilder.setId(resultSet.getInt("client_id"));
			
			return new Review.Builder()
					.setId(resultSet.getInt("id"))
					.setDescription(resultSet.getString("description"))
					.setClient(userBuilder.build())
					.build();

		} catch (SQLException e) {
			String message = String.format("Exception during create Review entity");
			logger.error(message, e);
			throw new RuntimeException(message, e);
		}
	}
	
	public static Application createApplication(ResultSet resultSet)  {
		try {
						
			MalfunctionType.Builder malfunctionTypeBuilder = new MalfunctionType.Builder();
			malfunctionTypeBuilder.setId(resultSet.getInt("malfunction_type"));
			
			User.Builder clientBuilder = new User.Builder();
			clientBuilder.setId(resultSet.getInt("client_id"));
			
			User.Builder managerBuilder = new User.Builder();
			managerBuilder.setId(resultSet.getInt("manager_id"));
			
			User.Builder masterBuilder = new User.Builder();
			masterBuilder.setId(resultSet.getInt("master_id"));
			
			return new Application.Builder()
					.setCreationDate(LocalDate.parse(resultSet.getString("creation_date")))
					.setMalfunctionType(malfunctionTypeBuilder.build())
					.setDescription(resultSet.getString("description"))
					.setClient(clientBuilder.build())
					.setManager(managerBuilder.build())
					.setMaster(masterBuilder.build())
					.setPrice(resultSet.getLong("price"))
					.setStatus(ApplicationStatus.valueOf(resultSet.getString("status")))
					.setCompletionDate(LocalDate.parse(resultSet.getString("completion_date")))
					.setServiceComment(resultSet.getString("service_comment"))
					.build();
					
		} catch (SQLException e) {
			String message = String.format("Exception during create Review entity");
			logger.error(message, e);
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
			logger.error(message, e);
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

	/*
	//TODO delete
	public static PeriodicalCategory createPeriodicalCategory(ResultSet resultSet)  {
		try {
			return new PeriodicalCategory.Builder()
					.setId(resultSet.getLong("id"))
					.setCategoryName(resultSet.getString("category_name"))
					.build();
		} catch (SQLException e) {
			String message = String.format("Exception during create periodical category entity");
			logger.error(message, e);
			throw new RuntimeException(message, e);
		}
	}

	
	public static Publisher createPublisher(ResultSet resultSet)  {
		try {
			return new Publisher.Builder()
					.setId(resultSet.getLong("id"))
					.setPublisher(resultSet.getString("publisher"))
					.build();
		} catch (SQLException e) {
			String message = String.format("Exception during create publisher entity");
			logger.error(message, e);
			throw new RuntimeException(message, e);
		}
	}

	
	public static Subscription createSubscription(ResultSet resultSet)  {
		try {

			User.Builder userBuilder = new User.Builder();
			userBuilder.setId(resultSet.getLong("user_id"));

			Periodical.Builder periodicalBuilder = new Periodical.Builder();
			periodicalBuilder.setId(resultSet.getLong("periodical_id"));

			return new Subscription.Builder()
					.setId(resultSet.getLong("id"))
					.setUser(userBuilder.build())
					.setPeriodical(periodicalBuilder.build())
					.setStartDate(LocalDate.parse(resultSet.getString("start_date")))
					.setNumberMonth(resultSet.getInt("number_month"))
					.setAddress(resultSet.getString("address"))
					.build();

		} catch (SQLException e) {
			String message = String.format("Exception during create subscription entity");
			logger.error(message, e);
			throw new RuntimeException(message, e);
		}
	}

	
	public static User createUser(ResultSet resultSet)  {
		try {
			return new User.Builder()
					.setId(resultSet.getLong("id"))
					.setName(resultSet.getString("name"))
					.setSurname(resultSet.getString("surname"))
					.setMobilePhone(resultSet.getString("mobile_phone"))
					.setEmail(resultSet.getString("email"))
					.setType(User.Type.valueOf(resultSet.getString("type").toUpperCase()))
					.setLogin(resultSet.getString("login"))
					.setPassword(resultSet.getString("password"))
					.build();

		} catch (SQLException e) {
			String message = String.format("Exception during create publisher user");
			logger.error(message, e);
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
			logger.error(message, e);
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
	
	/**
	 * Creates a new invoice using the data from the result set.
	 */
	/*

	public static Invoice createInvoice(ResultSet resultSet)  {
		try {

			Subscription.Builder subscriptionBuilder = new Subscription.Builder();
			subscriptionBuilder.setId(resultSet.getLong("subscription_id"));

			Invoice.Builder invoiceBuilder = new  Invoice.Builder();
			invoiceBuilder.setId(resultSet.getLong("id"))
			.setCost(resultSet.getLong("cost"))
			.setSubscription(subscriptionBuilder.build())
			.setCreationDate(LocalDate.parse(resultSet.getString("creation_date")))
			.setStatus(Invoice.Status.valueOf(resultSet.getString("status").toUpperCase()));

			if(resultSet.getString("payment_date")!= null) {
				invoiceBuilder.setPaymentDate(LocalDate.parse(resultSet.getString("payment_date")));
			}
			return invoiceBuilder.build();
		} catch (SQLException e) {
			String message = String.format("Exception during create invoice entity");
			logger.error(message, e);
			throw new RuntimeException(message, e);
		}
	}
	*/
}
