package com.dontsov.repairService.controller.commands.application;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dontsov.repairService.controller.commands.Command;
import com.dontsov.repairService.model.MalfunctionType;
import com.dontsov.repairService.service.ApplicationService;
import com.dontsov.repairService.service.MalfunctionTypeService;
import com.dontsov.repairService.service.ReviewService;
import com.dontsov.repairService.service.impl.ApplicationServiceImpl;
import com.dontsov.repairService.service.impl.MalfunctionTypeServiceImpl;
import com.dontsov.repairService.service.impl.ReviewServiceImpl;


public class DeleteApplication implements Command {

	private static final String APPLICATION_ID = "application";

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

		String id = request.getParameter(APPLICATION_ID);
		
		applicationService.deleteApplication(Integer.parseInt(id));
		return "/WEB-INF/view/home.jsp";
	}

}
