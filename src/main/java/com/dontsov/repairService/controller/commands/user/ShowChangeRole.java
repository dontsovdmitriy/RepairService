package com.dontsov.repairService.controller.commands.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.dontsov.repairService.controller.commands.Command;
import com.dontsov.repairService.model.User.Role;
import com.dontsov.repairService.service.UserService;
import com.dontsov.repairService.service.impl.UserServiceImpl;

public class ShowChangeRole implements Command {
	
	private static final String SUCCESSFUL_PAGE = "/WEB-INF/view/user/changeUserRole.jsp";

	private static final Logger LOGGER = Logger.getLogger(ShowChangeRole.class);

	private UserService userService;

	public ShowChangeRole() {
		this.userService = UserServiceImpl.getInstance();
	}
	public ShowChangeRole(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();	
		LOGGER.info("User " + session.getAttribute("user").toString() + " entered ShowChangeRole");

		request.setAttribute("userList", userService.getUsers());
		request.setAttribute("roleList", Role.values());
	
		return SUCCESSFUL_PAGE;
	}

}
