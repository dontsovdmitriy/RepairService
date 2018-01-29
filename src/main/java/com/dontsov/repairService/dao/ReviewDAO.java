package com.dontsov.repairService.dao;

import java.util.List;

import com.dontsov.repairService.model.Review;

public interface ReviewDAO extends GenericDAO<Review> {

	/**
	 * Return list of review for viewing with pagination
	 */
	List<Review> getReviewsPag(int offset);

	/**
	 * Return amount of reviews in DB
	 */
	int getReviewsAmmount();

}
