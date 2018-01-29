package com.dontsov.repairService.controller.commands.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.dontsov.repairService.controller.commands.Command;
import com.dontsov.repairService.model.User;
import com.dontsov.repairService.model.User.Role;
import com.dontsov.repairService.service.UserService;
import com.dontsov.repairService.service.impl.UserServiceImpl;


public class ChangeRole implements Command {

	private static final String USER_ID = "user";
	private static final String ROLE = "role";

	private static final String SUCCESSFUL_PAGE = "/WEB-INF/view/home.jsp";

	private static final Logger LOGGER = Logger.getLogger(ChangeRole.class);

	private UserService userService;

	public ChangeRole() {
		this.userService = UserServiceImpl.getInstance();
	}
	public ChangeRole(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		LOGGER.info("User " + session.getAttribute("user").toString() + " entered ChangeRole");

		String id = request.getParameter(USER_ID);
		String role = request.getParameter(ROLE);
		
		User user = new User.Builder()
				.setId(Integer.parseInt(id))
				.setRole(Role.valueOf(role))
				.build();

		userService.changeRole(user);
		return SUCCESSFUL_PAGE;
	}

}
