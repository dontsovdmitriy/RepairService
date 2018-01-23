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


public class AllMalfunctionTypes implements Command {

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
		
		HttpSession session = request.getSession();		
		session.setAttribute("malfunctionTypeList", malfunctionTypeService.getMalfunctionTypes());
				
		return "/WEB-INF/view/malfunctionType/malfunctionTypeView.jsp";
	}

}
