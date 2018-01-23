package com.dontsov.repairService.controller.commands.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dontsov.repairService.controller.commands.Command;
import com.dontsov.repairService.model.MalfunctionType;
import com.dontsov.repairService.model.Review;
import com.dontsov.repairService.model.User;
import com.dontsov.repairService.service.MalfunctionTypeService;
import com.dontsov.repairService.service.ReviewService;
import com.dontsov.repairService.service.UserService;
import com.dontsov.repairService.service.impl.MalfunctionTypeServiceImpl;
import com.dontsov.repairService.service.impl.ReviewServiceImpl;
import com.dontsov.repairService.service.impl.UserServiceImpl;


public class AddReview implements Command {

	private static final String DESCRIPTION = "description";

	private ReviewService reviewService;

	public AddReview() {
		this.reviewService = ReviewServiceImpl.getInstance();
	}
	public AddReview(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String description = request.getParameter(DESCRIPTION);
		User user = (User) session.getAttribute("user");
		
		//TODO Data validation
		
		
		Review review = new Review.Builder()
				.setDescription(description)
				.setClient(user)
				.build();
		
		reviewService.saveReview(review);
		return "/WEB-INF/view/home.jsp";
	}

}
