package com.dontsov.repairService.controller.commands.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.dontsov.repairService.controller.commands.Command;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains a method for user logout
 */
public class Logout implements Command {

	private static final String SUCCESSFUL_PAGE = "/WEB-INF/view/home.jsp";

	private static final Logger LOGGER = Logger.getLogger(Logout.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if (session.getAttribute("user") != null) {
			LOGGER.info("User " + session.getAttribute("user").toString() +  " logout successful");
			session.invalidate();
		} 
		
		return SUCCESSFUL_PAGE;
	}
}
