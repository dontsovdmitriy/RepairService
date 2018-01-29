package com.dontsov.repairService.dao;

import java.util.List;

import com.dontsov.repairService.model.Review;

public interface ReviewDAO extends GenericDAO<Review> {

	List<Review> getReviewsPag(int offset);

	int getReviewsAmmount();

}
