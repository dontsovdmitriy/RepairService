package com.dontsov.repairService.controller.commands.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dontsov.repairService.controller.commands.Command;
import com.dontsov.repairService.model.MalfunctionType;
import com.dontsov.repairService.service.MalfunctionTypeService;
import com.dontsov.repairService.service.UserService;
import com.dontsov.repairService.service.impl.MalfunctionTypeServiceImpl;
import com.dontsov.repairService.service.impl.UserServiceImpl;


public class DeleteUser implements Command {

	private static final String USER_ID = "user";

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

		String id = request.getParameter(USER_ID);

		userService.deleteUser(Integer.parseInt(id));
		return "/WEB-INF/view/home.jsp";
	}

}
