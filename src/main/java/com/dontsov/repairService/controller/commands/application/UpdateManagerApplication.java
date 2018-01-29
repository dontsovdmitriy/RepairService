package com.dontsov.repairService.controller.commands.application;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.dontsov.repairService.controller.commands.Command;
import com.dontsov.repairService.controller.validation.*;
import com.dontsov.repairService.model.*;
import com.dontsov.repairService.model.Application.ApplicationStatus;
import com.dontsov.repairService.service.ApplicationService;
import com.dontsov.repairService.service.impl.ApplicationServiceImpl;

public class UpdateManagerApplication implements Command {

	private static final String MASTER_ID = "master";
	private static final String PRICE = "price";
	private static final String SERVICE_COMMENT = "serviceComment";
	private static final String STATUS = "status";
	
	private static final String REGEX_EXCEP_PRICE = "exception.price";
	private static final String REGEX_EXCEP_SERVICE_COMMENT = "exception.serviceComment";

	private static final String VALIDATION_EXCEPTION_PAGE = "/WEB-INF/view/exceptionPage.jsp";
	private static final String SUCCESSFUL_PAGE = "/WEB-INF/view/home.jsp";

	private static final Logger LOGGER = Logger.getLogger(UpdateManagerApplication.class);

	private ApplicationService applicationService;
	private InputCheckingService checkingService;

	public UpdateManagerApplication() {
		this.applicationService = ApplicationServiceImpl.getInstance();
		this.checkingService = new InputCheckingServiceImpl();
	}
	
	public UpdateManagerApplication(ApplicationService applicationService, InputCheckingService checkingService) {
		this.applicationService = applicationService;
		this.checkingService = checkingService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String applicationId = request.getParameter("applicationId");

		String masterIdStr = request.getParameter(MASTER_ID);
		String priceStr = request.getParameter(PRICE);
		String serviceComment = request.getParameter(SERVICE_COMMENT);
		String status = request.getParameter(STATUS);
		
		if (status.equals("application.updateManagerApplication.inprogress")) {
			status = "INPROGRESS";
		} else {
			status = "CANCELED";
		}
		
		if(!checkingService.checkPrice(priceStr)){
			request.setAttribute("message", REGEX_EXCEP_PRICE);
			LOGGER.info(REGEX_EXCEP_PRICE);
			return VALIDATION_EXCEPTION_PAGE;
		}

		long price = Long.parseLong(priceStr.replaceAll("[,.]+", ""));

		if (price<0) {
			request.setAttribute("message", REGEX_EXCEP_PRICE);
			LOGGER.info(REGEX_EXCEP_PRICE);
			return VALIDATION_EXCEPTION_PAGE;
		}
		
		if(!(serviceComment == "") && !checkingService.checkDescription(serviceComment)){
			request.setAttribute("message", REGEX_EXCEP_SERVICE_COMMENT);
			LOGGER.info(REGEX_EXCEP_SERVICE_COMMENT);
			return VALIDATION_EXCEPTION_PAGE;
		}
		
		User master = new User.Builder()
				.setId(Integer.parseInt(masterIdStr))
				.build();
		
		Application application = new Application.Builder()
				.setMaster(master)
				.setPrice(price)
				.setServiceComment(serviceComment)
				.setStatus(ApplicationStatus.valueOf(status))
				.setManager(user)
				.setId(Integer.parseInt(applicationId))
				.build();
	
		applicationService.updateByManager(application);
		
		return SUCCESSFUL_PAGE;
				
	}
}
