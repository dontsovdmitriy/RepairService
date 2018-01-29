package com.dontsov.repairService.controller.commands.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.dontsov.repairService.controller.commands.Command;


/**
 * The class describes the {@code Command} interface implementation.
 * It contains a method for redirect to a login page
 */
public class ShowLogin implements Command {

	private static final String SUCCESSFUL_PAGE = "/WEB-INF/view/user/login.jsp";

    private static final Logger LOGGER = Logger.getLogger(ShowLogin.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LOGGER.info("User call ShowLogin");
		
		return SUCCESSFUL_PAGE;
	}
}
