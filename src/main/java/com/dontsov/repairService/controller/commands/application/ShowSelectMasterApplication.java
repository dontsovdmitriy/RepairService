package com.dontsov.repairService.controller.commands.application;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.dontsov.repairService.controller.commands.Command;
import com.dontsov.repairService.model.User;
import com.dontsov.repairService.service.ApplicationService;
import com.dontsov.repairService.service.impl.ApplicationServiceImpl;


public class ShowSelectMasterApplication implements Command {
	
	private static final String APPLICATION_STATUS = "INPROGRESS";
	private static final String SUCCESSFUL_PAGE = "/WEB-INF/view/application/selectApplicationMaster.jsp";

	private static final Logger LOGGER = Logger.getLogger(ShowSelectMasterApplication.class);

	private ApplicationService applicationService;

	public ShowSelectMasterApplication() {
		this.applicationService = ApplicationServiceImpl.getInstance();
	}
	public ShowSelectMasterApplication(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
				
		HttpSession session = request.getSession();	
		User user = (User) session.getAttribute("user");
		
		LOGGER.info("User " + session.getAttribute("user").toString() + " entered ShowSelectManagerApplication");

		request.setAttribute("applicationMasterList", applicationService.getApplicationsByStatusAndUser(APPLICATION_STATUS, user.getId()));
		
		return SUCCESSFUL_PAGE;
	}

}
