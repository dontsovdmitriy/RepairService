package com.dontsov.repairService.controller.commands.application;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.dontsov.repairService.controller.commands.Command;
import com.dontsov.repairService.model.Application;
import com.dontsov.repairService.model.Application.ApplicationStatus;
import com.dontsov.repairService.service.ApplicationService;
import com.dontsov.repairService.service.impl.ApplicationServiceImpl;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains a method for for updating applications by master
 */
public class UpdateMasterApplication implements Command {

	private static final String STATUS = "status";
	private static final String SUCCESSFUL_PAGE = "/WEB-INF/view/home.jsp";

	private static final Logger LOGGER = Logger.getLogger(UpdateMasterApplication.class);

	private ApplicationService applicationService;

	public UpdateMasterApplication() {
		this.applicationService = ApplicationServiceImpl.getInstance();
	}
	public UpdateMasterApplication(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		LOGGER.info("User " + session.getAttribute("user").toString() + " entered UpdateMasterApplication");

		String applicationId = request.getParameter("applicationId");

		String status = request.getParameter(STATUS);

		Application application = new Application.Builder()
				.setStatus(ApplicationStatus.valueOf(status))
				.setId(Integer.parseInt(applicationId))
				.build();
	
		applicationService.updateByMaster(application);
		
		return SUCCESSFUL_PAGE;
				
	}
}
