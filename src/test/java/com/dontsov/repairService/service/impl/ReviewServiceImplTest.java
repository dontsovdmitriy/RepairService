package com.dontsov.repairService.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.dontsov.repairService.dao.DaoConnection;
import com.dontsov.repairService.dao.DaoFactory;
import com.dontsov.repairService.dao.ReviewDAO;
import com.dontsov.repairService.dao.UtilDAO;
import com.dontsov.repairService.model.Review;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UtilDAO.class)
@PowerMockIgnore("javax.management.*")
public class ReviewServiceImplTest {

	@Mock
	private DaoFactory daoFactory;
	@Mock
	private DaoConnection connection;
	@Mock
	private ReviewDAO reviewDAO;
	@InjectMocks
	private ReviewServiceImpl reviewServiceImpl;

	@Test
	public void getReviews_test() {
		List<Review> reviews = new ArrayList<>();
		when(daoFactory.getConnection()).thenReturn(connection);
		when(daoFactory.createReviewDAO(connection)).thenReturn(reviewDAO);
		when(reviewDAO.getAll()).thenReturn(reviews);      
		List<Review> reviewsResult = reviewServiceImpl.getReviews();
		verify(daoFactory).getConnection();
		verify(daoFactory).createMalfunctionTypeDAO(connection);
		verify(reviewDAO).getAll();
		assertEquals(reviews, reviewsResult);	
	}
	
	@Test
	public void getReview_test() {
		Review review = new Review();
		Optional<Review> reviewOptional = Optional.of(review);
		when(daoFactory.getConnection()).thenReturn(connection);
		when(daoFactory.createReviewDAO(connection)).thenReturn(reviewDAO);
		when(reviewDAO.get(1)).thenReturn(reviewOptional);      
		Optional<Review> reviewResult = reviewServiceImpl.getReview(1);
		verify(daoFactory).getConnection();
		verify(daoFactory).createReviewDAO(connection);
		verify(reviewDAO).get(1);
		assertEquals(reviewOptional, reviewResult);	
	}
	
	@Test
	public void getReviewsAmmount_test() {
		when(daoFactory.getConnection()).thenReturn(connection);
		when(daoFactory.createReviewDAO(connection)).thenReturn(reviewDAO);
		when(reviewDAO.getReviewsAmmount()).thenReturn(5);      
		int ammountReviews = reviewServiceImpl.getReviewsAmmount();
		verify(daoFactory).getConnection();
		verify(daoFactory).createReviewDAO(connection);
		verify(reviewDAO).getReviewsAmmount();
		assertEquals(5, ammountReviews);	
	}
}
