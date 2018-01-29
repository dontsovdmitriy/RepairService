package com.dontsov.repairService.controller.commands.malfunctionType;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.dontsov.repairService.controller.commands.Command;

/**
 * The class describes the {@code Command} interface implementation.
 * It contains a method for showing page for adding malfunction type
 */
public class ShowAddMalfunctionType implements Command {

	private static final String SUCCESSFUL_PAGE = "/WEB-INF/view/malfunctionType/addMalfunctionType.jsp";

	private static final Logger LOGGER = Logger.getLogger(ShowAddMalfunctionType.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		LOGGER.info("User " + session.getAttribute("user").toString() + " entered ShowAddMalfunctionType");

		return SUCCESSFUL_PAGE;
	}

}
