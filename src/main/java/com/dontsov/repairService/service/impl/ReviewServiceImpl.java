package com.dontsov.repairService.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.dontsov.repairService.dao.*;
import com.dontsov.repairService.model.*;
import com.dontsov.repairService.service.ReviewService;

public class ReviewServiceImpl implements ReviewService {
	
	private static final Logger LOGGER = Logger.getLogger(ReviewServiceImpl.class);

	private DaoFactory daoFactory;

	private static class Holder{
		static final ReviewService INSTANCE = new ReviewServiceImpl( DaoFactory.getInstance() ); 
	}

	private ReviewServiceImpl(DaoFactory instance) {
		this.daoFactory = instance;
	}

	public static ReviewService getInstance(){
		return Holder.INSTANCE;
	}


	@Override
	public List<Review> getReviews() {
		try(DaoConnection connection = daoFactory.getConnection()){
			
			ReviewDAO reviewDAO = daoFactory.createReviewDAO(connection);
			
			LOGGER.info("Get all reviews");
			
			return reviewDAO.getAll();
			
		}
	}

	@Override
	public void saveReview(Review review) {
		try(DaoConnection connection = daoFactory.getConnection()){
			
			ReviewDAO reviewDAO = daoFactory.createReviewDAO(connection);
			
			LOGGER.info("Save review with client id= " + review.getClient().getId());
			
			reviewDAO.save(review);
		}		
	}

	@Override
	public Optional<Review> getReview(int id) {
		try(DaoConnection connection = daoFactory.getConnection()){
			
			ReviewDAO reviewDAO = daoFactory.createReviewDAO(connection);
			
			LOGGER.info("Get review with id = " + id);
			
			return reviewDAO.get(id);
		}
	}

	@Override
	public void deleteReview(int id) {
		try(DaoConnection connection = daoFactory.getConnection()){
			
			ReviewDAO reviewDAO = daoFactory.createReviewDAO(connection);
			
			LOGGER.info("Delete review with id = " + id);
			
			reviewDAO.delete(id);
		}		
	}

	@Override
	public List<Review> getReviewsPag(int offset) {
		try(DaoConnection connection = daoFactory.getConnection()){
			
			ReviewDAO reviewDAO = daoFactory.createReviewDAO(connection);
			
			LOGGER.info("Get all reviews with paggination");

			return reviewDAO.getReviewsPag(offset);
			
		}
	}

	@Override
	public int getReviewsAmmount() {
		try(DaoConnection connection = daoFactory.getConnection()){
			
			ReviewDAO reviewDAO = daoFactory.createReviewDAO(connection);
			
			LOGGER.info("Get ammount of reviews");
			
			return reviewDAO.getReviewsAmmount();
		}
	}

}
