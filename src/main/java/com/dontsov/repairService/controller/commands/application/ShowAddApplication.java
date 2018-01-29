package com.dontsov.repairService.controller.commands.application;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.dontsov.repairService.controller.commands.Command;
import com.dontsov.repairService.service.MalfunctionTypeService;
import com.dontsov.repairService.service.impl.MalfunctionTypeServiceImpl;


public class ShowAddApplication implements Command {
	
	private static final String SUCCESSFUL_PAGE = "/WEB-INF/view/application/addApplication.jsp";
	
	private static final Logger LOGGER = Logger.getLogger(ShowAddApplication.class);

	private MalfunctionTypeService malfunctionTypeService;

	public ShowAddApplication() {
		this.malfunctionTypeService = MalfunctionTypeServiceImpl.getInstance();
	}
	public ShowAddApplication(MalfunctionTypeService malfunctionTypeService) {
		this.malfunctionTypeService = malfunctionTypeService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		HttpSession session = request.getSession();	
		LOGGER.info("User " + session.getAttribute("user").toString() + " entered showAddApplication");

		request.setAttribute("malfunctionTypeList", malfunctionTypeService.getMalfunctionTypes());
		
		return SUCCESSFUL_PAGE;
	}

}
