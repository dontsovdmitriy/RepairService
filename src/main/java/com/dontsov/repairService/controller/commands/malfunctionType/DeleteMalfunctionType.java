package com.dontsov.repairService.controller.commands.malfunctionType;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dontsov.repairService.controller.commands.Command;
import com.dontsov.repairService.model.MalfunctionType;
import com.dontsov.repairService.service.MalfunctionTypeService;
import com.dontsov.repairService.service.impl.MalfunctionTypeServiceImpl;


public class DeleteMalfunctionType implements Command {

	private static final String MALFUNCTION_TYPE_ID = "malfunctionType";

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

		String id = request.getParameter(MALFUNCTION_TYPE_ID);

		malfunctionTypeService.deleteMalfunctionType(Integer.parseInt(id));
		return "/WEB-INF/view/home.jsp";
	}

}
