package com.dontsov.repairService.service;

import java.util.List;
import java.util.Optional;

import com.dontsov.repairService.model.Review;

public interface ReviewService {

	/**
     * Method return list of all reviews 
     */
	public List<Review> getReviews();

	/**
     * Method add review
     */
	public void saveReview(Review review);

	/**
     * Method return review by id
     */
	public Optional<Review> getReview(int id);

	/**
     * Method delete review by id
     */
	public void deleteReview(int theId);

	/**
     * Method return list of reviews for pagination view
     */
	public List<Review> getReviewsPag(int offset);

	/**
     * Method return amount of review in DB
     */
	public int getReviewsAmmount();
}
