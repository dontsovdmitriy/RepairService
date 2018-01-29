package com.dontsov.repairService.controller.commands.malfunctionType;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.dontsov.repairService.controller.commands.Command;
import com.dontsov.repairService.service.MalfunctionTypeService;
import com.dontsov.repairService.service.impl.MalfunctionTypeServiceImpl;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains a method for showing page for deleting malfunction type
 */
public class ShowDeleteMalfunctionType implements Command {
	
	private static final String SUCCESSFUL_PAGE = "/WEB-INF/view/malfunctionType/deleteMalfunctionType.jsp";

	private static final Logger LOGGER = Logger.getLogger(ShowDeleteMalfunctionType.class);

	private MalfunctionTypeService malfunctionTypeService;

	public ShowDeleteMalfunctionType() {
		this.malfunctionTypeService = MalfunctionTypeServiceImpl.getInstance();
	}
	public ShowDeleteMalfunctionType(MalfunctionTypeService malfunctionTypeService) {
		this.malfunctionTypeService = malfunctionTypeService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();	
		LOGGER.info("User " + session.getAttribute("user").toString() + " entered ShowDeleteMalfunctionType");

		request.setAttribute("malfunctionTypeList", malfunctionTypeService.getMalfunctionTypes());
		
		return SUCCESSFUL_PAGE;
	}

}
