package com.dontsov.repairService.controller.commands.application;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.dontsov.repairService.controller.commands.Command;
import com.dontsov.repairService.model.User;
import com.dontsov.repairService.service.ApplicationService;
import com.dontsov.repairService.service.impl.ApplicationServiceImpl;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains a method for showing my applications
 */
public class MyApplications implements Command {

	private static final int ROWS_AMMOUNT = 2;
	private static final String SUCCESSFUL_PAGE = "/WEB-INF/view/application/applicationView.jsp";

	private static final Logger LOGGER = Logger.getLogger(MyApplications.class);

	private ApplicationService applicationService;

	public MyApplications() {
		this.applicationService = ApplicationServiceImpl.getInstance();
	}
	public MyApplications(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();		
		User user = (User) session.getAttribute("user");

		LOGGER.info("User " + user.toString() + " entered my applications");

		int offset = 0;
		offset = getOffsetByUser(request, offset, user);
		request.setAttribute("applicationList", applicationService.getUserApplications(user,offset));
		request.setAttribute("fromMyApp", 1);
		return SUCCESSFUL_PAGE;
	}

	private int getOffsetByUser(HttpServletRequest request, int offset, User user) {
		if (request.getParameter("submit") != null) {
			int applicationsAmount =  applicationService.getApplicationsAmmountByUser(user);
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
