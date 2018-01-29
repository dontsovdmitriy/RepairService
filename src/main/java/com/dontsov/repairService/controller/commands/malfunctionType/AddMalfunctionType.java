package com.dontsov.repairService.controller.commands.malfunctionType;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.dontsov.repairService.controller.commands.Command;
import com.dontsov.repairService.controller.validation.*;
import com.dontsov.repairService.model.MalfunctionType;
import com.dontsov.repairService.service.MalfunctionTypeService;
import com.dontsov.repairService.service.impl.MalfunctionTypeServiceImpl;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains a method for adding malfunction type
 */
public class AddMalfunctionType implements Command {

	private static final String TYPE = "type";
	private static final String REPAIR_DAY = "repairDay";
	
	private static final String REGEX_EXCEP_TYPE = "exception.type";
	private static final String REGEX_EXCEP_REPAIR_DAY = "exception.repairDay";

	private static final String VALIDATION_EXCEPTION_PAGE = "/WEB-INF/view/exceptionPage.jsp";
	private static final String SUCCESSFUL_PAGE = "/WEB-INF/view/home.jsp";

	private static final Logger LOGGER = Logger.getLogger(AddMalfunctionType.class);

	private MalfunctionTypeService malfunctionTypeService;
	private InputCheckingService checkingService;

	public AddMalfunctionType() {
		this.malfunctionTypeService = MalfunctionTypeServiceImpl.getInstance();
		this.checkingService = new InputCheckingServiceImpl();
	}
	public AddMalfunctionType(MalfunctionTypeService malfunctionTypeService, InputCheckingService checkingService) {
		this.malfunctionTypeService = malfunctionTypeService;
		this.checkingService = checkingService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String type = request.getParameter(TYPE);
		String repairDay = request.getParameter(REPAIR_DAY);

		if(!checkingService.checkType(type)){
			request.setAttribute("message", REGEX_EXCEP_TYPE);
			LOGGER.info(REGEX_EXCEP_TYPE);
			return VALIDATION_EXCEPTION_PAGE;
		}
		
		if(!checkingService.checkRepairDay(repairDay)){
			request.setAttribute("message", REGEX_EXCEP_REPAIR_DAY);
			LOGGER.info(REGEX_EXCEP_REPAIR_DAY);
			return VALIDATION_EXCEPTION_PAGE;
		}
		
		MalfunctionType malfunctionType = new MalfunctionType.Builder()
				.setType(type)
				.setRepairDay(Integer.parseInt(repairDay))
				.build();
		
		malfunctionTypeService.saveMalfunctionType(malfunctionType);
		return SUCCESSFUL_PAGE;
	}

}
