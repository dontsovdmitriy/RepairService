package com.dontsov.repairService.controller.commands.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dontsov.repairService.controller.commands.Command;
import com.dontsov.repairService.model.MalfunctionType;
import com.dontsov.repairService.service.MalfunctionTypeService;
import com.dontsov.repairService.service.ReviewService;
import com.dontsov.repairService.service.impl.MalfunctionTypeServiceImpl;
import com.dontsov.repairService.service.impl.ReviewServiceImpl;


public class ShowDeleteReview implements Command {
	
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
		session.setAttribute("reviewList", reviewService.getReviews());
		
		return "/WEB-INF/view/review/deleteReview.jsp";
	}

}
