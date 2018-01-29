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
 * It contains a method for showing malfunction type
 */
public class AllMalfunctionTypes implements Command {

	private static final String SUCCESSFUL_PAGE = "/WEB-INF/view/malfunctionType/malfunctionTypeView.jsp";

	private static final Logger LOGGER = Logger.getLogger(AllMalfunctionTypes.class);

	private MalfunctionTypeService malfunctionTypeService;

	public AllMalfunctionTypes() {
		this.malfunctionTypeService = MalfunctionTypeServiceImpl.getInstance();
	}
	public AllMalfunctionTypes(MalfunctionTypeService malfunctionTypeService) {
		this.malfunctionTypeService = malfunctionTypeService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		LOGGER.info(" User entered AllMalfunctionTypes");

		request.setAttribute("malfunctionTypeList", malfunctionTypeService.getMalfunctionTypes());
				
		return SUCCESSFUL_PAGE;
	}

}
