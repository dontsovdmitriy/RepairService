package com.dontsov.repairService.controller.commands.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.dontsov.repairService.controller.commands.Command;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains a method for showing page for add review
 */
public class ShowAddReview implements Command {

	private static final String SUCCESSFUL_PAGE = "/WEB-INF/view/review/addReview.jsp";

	private static final Logger LOGGER = Logger.getLogger(ShowAddReview.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		LOGGER.info("User " + session.getAttribute("user").toString() + " entered ShowAddReview");

		return SUCCESSFUL_PAGE;
	}

}
