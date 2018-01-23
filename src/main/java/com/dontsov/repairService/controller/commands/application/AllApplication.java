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


public class AllApplication implements Command {

	private ApplicationService applicationService;

	public AllApplication() {
		this.applicationService = ApplicationServiceImpl.getInstance();
	}
	public AllApplication(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();		
		session.setAttribute("applicationList", applicationService.getApplications());		
		return "/WEB-INF/view/application/applicationView.jsp";
	}

}
