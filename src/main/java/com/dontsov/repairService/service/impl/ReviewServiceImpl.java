package com.dontsov.repairService.service.impl;

import java.util.List;
import java.util.Optional;

import com.dontsov.repairService.dao.DaoConnection;
import com.dontsov.repairService.dao.DaoFactory;
import com.dontsov.repairService.dao.ReviewDAO;
import com.dontsov.repairService.model.Review;
import com.dontsov.repairService.model.User;
import com.dontsov.repairService.service.ReviewService;
import com.dontsov.repairService.service.UserService;

public class ReviewServiceImpl implements ReviewService {
	
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


	public List<Review> getReviews() {
		try(DaoConnection connection = daoFactory.getConnection()){
			ReviewDAO reviewDAO = daoFactory.createReviewDAO(connection);
		//	logger.info("Searching invoice with subscription id = " + subscription.getId());
			
			//TODO rollback etd
			//TODO тут подумать хорошо что бы не нарваться на nullpointer
			
			UserService userService = UserServiceImpl.getInstance();

			List<Review> reviews = reviewDAO.getAll();
			
			for (Review review : reviews) {

				User client = userService.getUser(review.getClient().getId()).get();
				
				review.setClient(client);
			}
			return reviews;
		}
	}

	public void saveReview(Review review) {
		try(DaoConnection connection = daoFactory.getConnection()){
			ReviewDAO reviewDAO = daoFactory.createReviewDAO(connection);
		//	logger.info("Searching invoice with subscription id = " + subscription.getId());
			reviewDAO.save(review);
		}		
	}

	public Optional<Review> getReview(int id) {
		try(DaoConnection connection = daoFactory.getConnection()){
			ReviewDAO reviewDAO = daoFactory.createReviewDAO(connection);
		//	logger.info("Searching invoice with subscription id = " + subscription.getId());
			return reviewDAO.get(id);
		}
	}

	public void deleteReview(int id) {
		try(DaoConnection connection = daoFactory.getConnection()){
			ReviewDAO reviewDAO = daoFactory.createReviewDAO(connection);
		//	logger.info("Searching invoice with subscription id = " + subscription.getId());
			reviewDAO.delete(id);
		}		
	}

}
