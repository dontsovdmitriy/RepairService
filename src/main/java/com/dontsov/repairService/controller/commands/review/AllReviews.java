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
 * It contains a method for showing all reviews
 */
public class AllReviews implements Command {

	private static final int ROWS_AMMOUNT = 3;
	
	private static final String SUCCESSFUL_PAGE = "/WEB-INF/view/review/reviewView.jsp";

	private static final Logger LOGGER = Logger.getLogger(AllReviews.class);

	private ReviewService reviewService;

	public AllReviews() {
		this.reviewService = ReviewServiceImpl.getInstance();
	}
	public AllReviews(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		LOGGER.info("User entered AllReviews");

		int offset = 0;
		offset = getOffset(request, offset);
		request.setAttribute("reviewList", reviewService.getReviewsPag(offset));		
		request.setAttribute("offset", offset);

		return SUCCESSFUL_PAGE;

	}
	
	private int getOffset(HttpServletRequest request, int offset) {
		if (request.getParameter("submit") != null) {
			int applicationsAmount =  reviewService.getReviewsAmmount();

			offset = Integer.parseInt(request.getParameter("offset"));
			
			if (((offset + ROWS_AMMOUNT) < applicationsAmount) && ((request.getParameter("submit").equals("Next")) || (request.getParameter("submit").equals("Следующая")))  ) {
				offset += ROWS_AMMOUNT;
			}
			
			if (request.getParameter("submit").equals("Previous") || (request.getParameter("submit").equals("Предыдущая"))  ) {
				offset -= ROWS_AMMOUNT;
			}
			
			if (offset<0) {
				offset = 0;
			}
			
		}
		return offset;

	}

}
