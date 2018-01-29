package com.dontsov.repairService.controller.commands.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.dontsov.repairService.controller.commands.Command;
import com.dontsov.repairService.controller.commands.application.AllApplications;
import com.dontsov.repairService.service.ReviewService;
import com.dontsov.repairService.service.impl.ReviewServiceImpl;

public class ShowDeleteReview implements Command {
	
	private static final String SUCCESSFUL_PAGE = "/WEB-INF/view/review/deleteReview.jsp";

	private static final Logger LOGGER = Logger.getLogger(AllApplications.class);

	private ReviewService reviewService;

	public ShowDeleteReview() {
		this.reviewService = ReviewServiceImpl.getInstance();
	}
	public ShowDeleteReview(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();		
		LOGGER.info("User " + session.getAttribute("user").toString() + " entered ShowDeleteReview");

		request.setAttribute("reviewList", reviewService.getReviews());
		
		return SUCCESSFUL_PAGE;
	}

}
