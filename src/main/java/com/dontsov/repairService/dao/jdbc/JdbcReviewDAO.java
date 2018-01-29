package com.dontsov.repairService.dao.jdbc;

import java.sql.*;
import java.util.*;

import org.apache.log4j.Logger;

import com.dontsov.repairService.dao.*;
import com.dontsov.repairService.model.Review;

public class JdbcReviewDAO implements ReviewDAO {

	private static final int PAG_LIMIT = 2;

	private static final String INSERT_REVIEW = "INSERT INTO review " + "(description, client_id) " + "VALUES (?, ?)";
	private static final String DELETE_REVIEW = "DELETE FROM review WHERE id = ?";
	private static final String SELECT_ALL_REVIEWS = "SELECT "
				+ "rv.id AS rv_id, "
				+ "rv.description AS rv_description, "
				+ "cl.id AS cl_id, "
				+ "cl.username AS cl_username "
			+ "FROM repair_service.review AS rv "
			+ "LEFT JOIN repair_service.user AS cl ON (cl.id = rv.client_id)";
	private static final String SELECT_ALL_REVIEWS_PAG = "SELECT "
				+ "rv.id AS rv_id, "
				+ "rv.description AS rv_description, "
				+ "cl.id AS cl_id, "
				+ "cl.username AS cl_username "
			+ "FROM repair_service.review AS rv "
			+ "LEFT JOIN repair_service.user AS cl ON (cl.id = rv.client_id) "
			+ "ORDER BY rv.id ASC LIMIT " + PAG_LIMIT + " OFFSET %d";
	private static final String SELECT_REVIEWS_AMMOUNT = "SELECT COUNT(*) FROM review";

	private static final Logger LOGGER = Logger.getLogger(JdbcReviewDAO.class);

	private Connection connection;

	JdbcReviewDAO(Connection connection) {
		this.connection = connection;
	}

	public List<Review> getAll() {
		List<Review> resultList = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_REVIEWS)) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				resultList.add(UtilDAO.createReview(rs));
			}

		} catch (SQLException e) {
			String message = String.format("Exception during find all Reviews");
			LOGGER.error( message , e);
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
			String message = String.format("Exception during add a review by user with id = ", review.getId());
			LOGGER.error( message , e);
			throw new RuntimeException(message, e);
		}
	}

	public Optional<Review> get(int id) {
		return null;
	}

	public void delete(int id) {
		try (PreparedStatement ps = connection.prepareStatement(DELETE_REVIEW)){

			ps.setInt(1, id);

			ps.executeUpdate();

		} catch (SQLException e) {
			String message = String.format("Exception during delete a review with id = %s", id);
			LOGGER.error( message , e);
			throw new RuntimeException(message, e);
		}
	}

	@Override
	public List<Review> getReviewsPag(int offset) {
		List<Review> resultList = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(String.format(SELECT_ALL_REVIEWS_PAG, offset))) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				resultList.add(UtilDAO.createReview(rs));
			}

		} catch (SQLException e) {
			String message = String.format("Exception during find all reviews with offset = " + offset);
			LOGGER.error( message , e);
			throw new RuntimeException(message, e);
		}
		return resultList;
	}

	@Override
	public int getReviewsAmmount() {
		int reviewsAmmount = 0;
		try (PreparedStatement ps = connection.prepareStatement(SELECT_REVIEWS_AMMOUNT)) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				reviewsAmmount = rs.getInt(1);
			}

		} catch (SQLException e) {
			String message = String.format("Exception during get ammount of reviews");
			LOGGER.error( message , e);
			throw new RuntimeException(message, e);
		}
		return reviewsAmmount;
	}
}
