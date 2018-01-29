package com.dontsov.repairService.controller.commands.application;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.dontsov.repairService.controller.commands.Command;
import com.dontsov.repairService.service.ApplicationService;
import com.dontsov.repairService.service.impl.ApplicationServiceImpl;

public class DeleteApplication implements Command {

	private static final String APPLICATION_ID = "application";
	private static final String SUCCESSFUL_PAGE = "/WEB-INF/view/home.jsp";

	private static final Logger LOGGER = Logger.getLogger(DeleteApplication.class);

	private ApplicationService applicationService;

	public DeleteApplication() {
		this.applicationService = ApplicationServiceImpl.getInstance();
	}
	public DeleteApplication(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		LOGGER.info("User " + session.getAttribute("user").toString() + " entered DeleteApplication");

		String id = request.getParameter(APPLICATION_ID);
		
		applicationService.deleteApplication(Integer.parseInt(id));
		return SUCCESSFUL_PAGE;
	}

}
