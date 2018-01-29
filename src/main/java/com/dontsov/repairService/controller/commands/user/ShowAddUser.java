package com.dontsov.repairService.controller.commands.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.dontsov.repairService.controller.commands.Command;

public class ShowAddUser implements Command {

	private static final String SUCCESSFUL_PAGE = "/WEB-INF/view/user/addUser.jsp";
	
	private static final Logger LOGGER = Logger.getLogger(ShowAddUser.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		LOGGER.info("User call ShowAddUser");

		return SUCCESSFUL_PAGE;
	}

}
