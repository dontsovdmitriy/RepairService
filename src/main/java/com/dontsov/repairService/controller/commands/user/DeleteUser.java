package com.dontsov.repairService.controller.commands.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.dontsov.repairService.controller.commands.Command;
import com.dontsov.repairService.service.UserService;
import com.dontsov.repairService.service.impl.UserServiceImpl;

public class DeleteUser implements Command {

	private static final String USER_ID = "user";
	
	private static final String SUCCESSFUL_PAGE = "/WEB-INF/view/home.jsp";
	
	private static final Logger LOGGER = Logger.getLogger(DeleteUser.class);

	private UserService userService;

	public DeleteUser() {
		this.userService = UserServiceImpl.getInstance();
	}
	public DeleteUser(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		LOGGER.info("User " + session.getAttribute("user").toString() + " entered DeleteUser");

		String id = request.getParameter(USER_ID);

		userService.deleteUser(Integer.parseInt(id));
		
		return SUCCESSFUL_PAGE;
	}

}
