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
 * It contains a method for showing all applications
 */
public class AllApplications implements Command {

	private static final int ROWS_AMMOUNT = 2;
	private static final String SUCCESSFUL_PAGE = "/WEB-INF/view/application/applicationView.jsp";

	private static final Logger LOGGER = Logger.getLogger(AllApplications.class);

	private ApplicationService applicationService;

	public AllApplications() {
		this.applicationService = ApplicationServiceImpl.getInstance();
	}
	public AllApplications(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int offset = 0;
		offset = getOffset(request, offset);
		request.setAttribute("applicationList", applicationService.getAllApplicationsPag(offset));		
		request.setAttribute("offset", offset);
		LOGGER.info("User " + session.getAttribute("user").toString() + " entered AllApplications");
		return SUCCESSFUL_PAGE;

	}

	private int getOffset(HttpServletRequest request, int offset) {
		if (request.getParameter("submit") != null) {
			int applicationsAmount =  applicationService.getApplicationsAmmount();
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
