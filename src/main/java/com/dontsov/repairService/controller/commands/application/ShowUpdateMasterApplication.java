package com.dontsov.repairService.controller.commands.application;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.dontsov.repairService.controller.commands.Command;

public class ShowUpdateMasterApplication implements Command {

	private static final String APPLICATION_ID = "application";
	private static final String SUCCESSFUL_PAGE = "/WEB-INF/view/application/updateMasterApplication.jsp";
		
	private static final Logger LOGGER = Logger.getLogger(ShowUpdateMasterApplication.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		LOGGER.info("User " + session.getAttribute("user").toString() + " entered ShowUpdateMasterApplication");
	
		request.setAttribute("applicationId", request.getParameter(APPLICATION_ID));
	
		return SUCCESSFUL_PAGE;
	}

}
