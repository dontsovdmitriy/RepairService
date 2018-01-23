package com.dontsov.repairService.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dontsov.repairService.dao.ReviewDAO;
import com.dontsov.repairService.dao.UtilDao;
import com.dontsov.repairService.model.Review;
import com.dontsov.repairService.model.User;


public class JdbcReviewDAO implements ReviewDAO {

	//TODO Logger 
	//TODO static variable

	private static final String INSERT_REVIEW = "INSERT INTO review " + "(description, client_id) " + "VALUES (?, ?)";
	private static final String DELETE_REVIEW = "DELETE FROM review WHERE id = ?";
	private static final String SELECT_ALL_REVIEWS = "SELECT * FROM review";

	private Connection connection;

	JdbcReviewDAO(Connection connection) {
		this.connection = connection;
	}

	public List<Review> getAll() {
		List<Review> resultList = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_REVIEWS)) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				resultList.add(UtilDao.createReview(rs));
			}

		} catch (SQLException e) {
			String message = String.format("Exception during find all Reviews");
			//	logger.error( message , e);
			throw new RuntimeException(message, e);
		}
		return resultList;
	}

	public void save(Review review) {
		try (PreparedStatement ps = connection.prepareStatement(INSERT_REVIEW )){

			ps.setString(1, review.getDescription());
			ps.setInt(2, review.getClient().getId());

			ps.executeUpdate();

		} catch (SQLException e) {
			String message = String.format("Exception during add a user with description = %s", review.getDescription());
			//		logger.error( message , e);
			throw new RuntimeException(message, e);
		}
	}

	public Optional<Review> get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(int id) {
		try (PreparedStatement ps = connection.prepareStatement(DELETE_REVIEW)){

			ps.setInt(1, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			String message = String.format("Exception during delete a review with id = %s", id);
			//	logger.error( message , e);
			throw new RuntimeException(message, e);
		}
	}

}
