package com.dontsov.repairService.controller.commands.application;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.dontsov.repairService.controller.commands.Command;
import com.dontsov.repairService.model.User;
import com.dontsov.repairService.service.UserService;
import com.dontsov.repairService.service.impl.UserServiceImpl;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains a method for showing page for updating application by manager
 */
public class ShowUpdateManagerApplication implements Command {

	private static final String APPLICATION_ID = "application";
	private static final String SUCCESSFUL_PAGE = "/WEB-INF/view/application/updateManagerApplication.jsp";

	private static final Logger LOGGER = Logger.getLogger(ShowUpdateManagerApplication.class);

	private UserService userService;

	public ShowUpdateManagerApplication() {
		this.userService = UserServiceImpl.getInstance();
	}
	public ShowUpdateManagerApplication(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		LOGGER.info("User " + session.getAttribute("user").toString() + " entered ShowUpdateManagerApplication");

		request.setAttribute("applicationId", request.getParameter(APPLICATION_ID));		
		request.setAttribute("masterList", userService.getUserByRole(User.Role.MASTER));

		return SUCCESSFUL_PAGE;
	}

}
