package com.dontsov.repairService.controller.commands.application;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.dontsov.repairService.controller.commands.Command;
import com.dontsov.repairService.controller.validation.*;
import com.dontsov.repairService.model.*;

import com.dontsov.repairService.model.Application.ApplicationStatus;
import com.dontsov.repairService.service.*;
import com.dontsov.repairService.service.impl.*;


public class AddApplication implements Command {

	private static final String MALFUNCTION_TYPE = "malfunctionType";
	private static final String DESCRIPTION = "description";
	private static final String REGEX_EXCEP_DESCRIPTION = "exception.description";
	private static final String VALIDATION_EXCEPTION_PAGE = "/WEB-INF/view/exceptionPage.jsp";
	private static final String SUCCESSFUL_PAGE = "/WEB-INF/view/home.jsp";

	private static final Logger LOGGER = Logger.getLogger(AddApplication.class);

	private ApplicationService applicationService;
	private MalfunctionTypeService malfunctionTypeService;
	private InputCheckingService checkingService;

	public AddApplication() {
		this.applicationService = ApplicationServiceImpl.getInstance();
		this.malfunctionTypeService = MalfunctionTypeServiceImpl.getInstance();
		this.checkingService = new InputCheckingServiceImpl();
	}
	
	public AddApplication(ApplicationService applicationService, MalfunctionTypeService malfunctionTypeService, InputCheckingService checkingService) {
		this.applicationService = applicationService;
		this.malfunctionTypeService = malfunctionTypeService;
		this.checkingService = checkingService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String malfunctionTypeReq = request.getParameter(MALFUNCTION_TYPE);
		String description = request.getParameter(DESCRIPTION);

		User user = (User) session.getAttribute("user");
		
		MalfunctionType malfunctionType = new MalfunctionType.Builder()
				.setId(Integer.parseInt(malfunctionTypeReq))
				.setRepairDay(malfunctionTypeService.getMalfunctionType(Integer.parseInt(malfunctionTypeReq)).get().getRepairDay())
				.build();
		
		if(!(description == "") && !checkingService.checkDescription(description)){
			request.setAttribute("message", REGEX_EXCEP_DESCRIPTION);
			LOGGER.info(REGEX_EXCEP_DESCRIPTION);
			return VALIDATION_EXCEPTION_PAGE;
		}
		
		Application application = new Application.Builder()
				.setCreationDate(LocalDate.now())
				.setMalfunctionType(malfunctionType)
				.setStatus(ApplicationStatus.TODO)
				.setDescription(description)
				.setClient(user)
				.setCompletionDate(LocalDate.now().plusDays(malfunctionType.getRepairDay()))
				.build();
		
		applicationService.saveApplication(application);
		return SUCCESSFUL_PAGE;
	}

}
