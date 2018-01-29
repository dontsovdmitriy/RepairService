package com.dontsov.repairService.controller.commands.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.dontsov.repairService.controller.commands.Command;
import com.dontsov.repairService.service.ReviewService;
import com.dontsov.repairService.service.impl.ReviewServiceImpl;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains a method for delete review
 */
public class DeleteReview implements Command {

	private static final String REVIEW_ID = "review";

	private static final String SUCCESSFUL_PAGE = "/WEB-INF/view/home.jsp";

	private static final Logger LOGGER = Logger.getLogger(DeleteReview.class);

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
		
		LOGGER.info("User " + session.getAttribute("user").toString() + " entered DeleteReview");

		String id = request.getParameter(REVIEW_ID);

		reviewService.deleteReview(Integer.parseInt(id));
		
		return SUCCESSFUL_PAGE;
	}

}
