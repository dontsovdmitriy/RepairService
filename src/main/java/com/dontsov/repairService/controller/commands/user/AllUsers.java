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
 * It contains a method for showing all users
 */
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
		request.setAttribute("offset", offset);

		return SUCCESSFUL_PAGE;
	}

	private int getOffset(HttpServletRequest request, int offset) {
		if (request.getParameter("submit") != null) {
			int applicationsAmount =  userService.getUsersAmmount();

			offset = Integer.parseInt(request.getParameter("offset"));
			
			if (((offset + ROWS_AMMOUNT) < applicationsAmount) && ((request.getParameter("submit").equals("Next")) || (request.getParameter("submit").equals("Следующая")))  ) {
				offset += ROWS_AMMOUNT;
			}
			
			if (request.getParameter("submit").equals("Previous") || (request.getParameter("submit").equals("Предыдущая"))  ) {
				offset -= ROWS_AMMOUNT;
			}
			
			if (offset<0) {
				offset = 0;
			}
			
		}
		return offset;

	}
}
