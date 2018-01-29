package com.dontsov.repairService.controller.commands.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.dontsov.repairService.controller.commands.Command;
import com.dontsov.repairService.service.UserService;
import com.dontsov.repairService.service.impl.UserServiceImpl;

public class AllUsers implements Command {

	private static final int ROWS_AMMOUNT = 2;
	
	private static final String SUCCESSFUL_PAGE = "/WEB-INF/view/user/userView.jsp";

	private static final Logger LOGGER = Logger.getLogger(AllUsers.class);

	private UserService userService;

	public AllUsers() {
		this.userService = UserServiceImpl.getInstance();
	}
	public AllUsers(UserService userService) {
		this.userService = userService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();		
		LOGGER.info("User " + session.getAttribute("user").toString() + " entered AllUsers");

		int offset = 0;
		offset = getOffset(request, offset);

		request.setAttribute("userList", userService.getUsersPag(offset));

		return SUCCESSFUL_PAGE;
	}

	private int getOffset(HttpServletRequest request, int offset) {
		if (request.getParameter("submit") != null) {
			int applicationsAmount =  userService.getUsersAmmount();

			offset = Integer.parseInt(request.getParameter("offset"));
			offset += ((request.getParameter("submit").equals("Next")) || (request.getParameter("submit").equals("Следующая"))) ? ROWS_AMMOUNT : -ROWS_AMMOUNT;

			if ((offset + ROWS_AMMOUNT) >= applicationsAmount  ) {
				offset = applicationsAmount-ROWS_AMMOUNT;
			}

			if (offset<0) {
				offset = 0;
			}
			
			request.setAttribute("offset", offset);
		}
		return offset;

	}
}
