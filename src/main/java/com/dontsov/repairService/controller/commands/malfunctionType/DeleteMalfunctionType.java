package com.dontsov.repairService.controller.commands.malfunctionType;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.dontsov.repairService.controller.commands.Command;
import com.dontsov.repairService.service.MalfunctionTypeService;
import com.dontsov.repairService.service.impl.MalfunctionTypeServiceImpl;


public class DeleteMalfunctionType implements Command {

	private static final String MALFUNCTION_TYPE_ID = "malfunctionType";
	
	private static final String SUCCESSFUL_PAGE = "/WEB-INF/view/home.jsp";
	
	private static final Logger LOGGER = Logger.getLogger(DeleteMalfunctionType.class);

	private MalfunctionTypeService malfunctionTypeService;

	public DeleteMalfunctionType() {
		this.malfunctionTypeService = MalfunctionTypeServiceImpl.getInstance();
	}
	public DeleteMalfunctionType(MalfunctionTypeService malfunctionTypeService) {
		this.malfunctionTypeService = malfunctionTypeService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		
	//	LOGGER.info("User " + session.getAttribute("user").toString() + " entered DeleteMalfunctionType");

		String id = request.getParameter(MALFUNCTION_TYPE_ID);

		malfunctionTypeService.deleteMalfunctionType(Integer.parseInt(id));
		
		return SUCCESSFUL_PAGE;
	}

}
