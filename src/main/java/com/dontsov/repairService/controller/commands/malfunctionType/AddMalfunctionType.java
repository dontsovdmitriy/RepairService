package com.dontsov.repairService.controller.commands.malfunctionType;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dontsov.repairService.controller.commands.Command;
import com.dontsov.repairService.model.MalfunctionType;
import com.dontsov.repairService.service.MalfunctionTypeService;
import com.dontsov.repairService.service.UserService;
import com.dontsov.repairService.service.impl.MalfunctionTypeServiceImpl;
import com.dontsov.repairService.service.impl.UserServiceImpl;


public class AddMalfunctionType implements Command {

	private static final String TYPE = "type";
	private static final String REPAIR_DAY = "repairDay";

	private MalfunctionTypeService malfunctionTypeService;

	public AddMalfunctionType() {
		this.malfunctionTypeService = MalfunctionTypeServiceImpl.getInstance();
	}
	public AddMalfunctionType(MalfunctionTypeService malfunctionTypeService) {
		this.malfunctionTypeService = malfunctionTypeService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String type = request.getParameter(TYPE);
		String repairDay = request.getParameter(REPAIR_DAY);

		//TODO Data validation

		MalfunctionType malfunctionType = new MalfunctionType.Builder()
				.setType(type)
				.setRepairDay(Integer.parseInt(repairDay))
				.build();
		
		malfunctionTypeService.saveMalfunctionType(malfunctionType);
		return "/WEB-INF/view/home.jsp";
	}

}
