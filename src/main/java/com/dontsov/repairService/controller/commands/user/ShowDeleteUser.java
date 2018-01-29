package com.dontsov.repairService.controller.commands.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.dontsov.repairService.controller.commands.Command;
import com.dontsov.repairService.service.UserService;
import com.dontsov.repairService.service.impl.UserServiceImpl;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains a method for showing page for delete user
 */
public class ShowDeleteUser implements Command {
	
	private static final String SUCCESSFUL_PAGE = "/WEB-INF/view/user/deleteUser.jsp";

	private static final Logger LOGGER = Logger.getLogger(ShowDeleteUser.class);

	private UserService userService;

	public ShowDeleteUser() {
		this.userService = UserServiceImpl.getInstance();
	}
	public ShowDeleteUser(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();		
		LOGGER.info("User " + session.getAttribute("user").toString() + " entered ShowDeleteUser");

		request.setAttribute("userList", userService.getUsers());
		
		return SUCCESSFUL_PAGE;
	}

}
