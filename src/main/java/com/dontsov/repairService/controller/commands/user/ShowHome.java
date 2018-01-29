package com.dontsov.repairService.controller.commands.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.dontsov.repairService.controller.commands.Command;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains a method for showing home page
 */
public class ShowHome implements Command {

	private static final String SUCCESSFUL_PAGE = "/WEB-INF/view/home.jsp";

	private static final Logger LOGGER = Logger.getLogger(ShowHome.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		LOGGER.info("User " + session.getAttribute("user").toString() + " entered ShowHome");

		return SUCCESSFUL_PAGE;
	}

}
