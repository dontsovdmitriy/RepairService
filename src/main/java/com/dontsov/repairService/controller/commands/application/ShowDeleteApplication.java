package com.dontsov.repairService.controller.commands.application;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.dontsov.repairService.controller.commands.Command;
import com.dontsov.repairService.service.ApplicationService;
import com.dontsov.repairService.service.impl.ApplicationServiceImpl;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains a method for showing page for deleting application
 */
public class ShowDeleteApplication implements Command {

	private static final String SUCCESSFUL_PAGE = "/WEB-INF/view/application/deleteApplication.jsp";
	private static final Logger LOGGER = Logger.getLogger(ShowDeleteApplication.class);

	private ApplicationService applicationService;

	public ShowDeleteApplication() {
		this.applicationService = ApplicationServiceImpl.getInstance();
	}
	public ShowDeleteApplication(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();		
		LOGGER.info("User " + session.getAttribute("user").toString() + " entered showDeleteApplication");

		request.setAttribute("applicationList", applicationService.getApplications());
		
		return SUCCESSFUL_PAGE;
	}

}
