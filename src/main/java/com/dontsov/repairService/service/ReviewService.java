package com.dontsov.repairService.service;

import java.util.List;
import java.util.Optional;

import com.dontsov.repairService.model.Review;

public interface ReviewService {

	public List<Review> getReviews();

	public void saveReview(Review review);

	public Optional<Review> getReview(int id);

	public void deleteReview(int theId);
}
