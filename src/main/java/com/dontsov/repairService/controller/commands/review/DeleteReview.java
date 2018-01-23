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


public class DeleteReview implements Command {

	private static final String REVIEW_ID = "review";

	private ReviewService reviewService;

	public DeleteReview() {
		this.reviewService = ReviewServiceImpl.getInstance();
	}
	public DeleteReview(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String id = request.getParameter(REVIEW_ID);

		reviewService.deleteReview(Integer.parseInt(id));
		return "/WEB-INF/view/home.jsp";
	}

}
