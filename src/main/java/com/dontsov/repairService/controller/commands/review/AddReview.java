package com.dontsov.repairService.controller.commands.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.dontsov.repairService.controller.commands.Command;
import com.dontsov.repairService.controller.validation.*;
import com.dontsov.repairService.model.*;
import com.dontsov.repairService.service.ReviewService;
import com.dontsov.repairService.service.impl.ReviewServiceImpl;


public class AddReview implements Command {

	private static final String DESCRIPTION = "description";
	private static final String REGEX_EXCEP_DESCRIPTION = "exception.description";

	private static final String VALIDATION_EXCEPTION_PAGE = "/WEB-INF/view/exceptionPage.jsp";
	private static final String SUCCESSFUL_PAGE = "/WEB-INF/view/home.jsp";

	private static final Logger LOGGER = Logger.getLogger(AddReview.class);

	private ReviewService reviewService;
	private InputCheckingService checkingService;

	public AddReview() {
		this.reviewService = ReviewServiceImpl.getInstance();
		this.checkingService = new InputCheckingServiceImpl();
	}
	public AddReview(ReviewService reviewService, InputCheckingService checkingService) {
		this.reviewService = reviewService;
		this.checkingService = checkingService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String description = request.getParameter(DESCRIPTION);
		User user = (User) session.getAttribute("user");
		
		if(!(description == "") && !checkingService.checkDescription(description)){
			request.setAttribute("message", REGEX_EXCEP_DESCRIPTION);
			LOGGER.info(REGEX_EXCEP_DESCRIPTION);
			return VALIDATION_EXCEPTION_PAGE;
		}		
		
		Review review = new Review.Builder()
				.setDescription(description)
				.setClient(user)
				.build();
		
		reviewService.saveReview(review);
		
		return SUCCESSFUL_PAGE;
	}
}
