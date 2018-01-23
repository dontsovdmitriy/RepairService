package com.dontsov.repairService.controller.commands.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dontsov.repairService.controller.commands.Command;
import com.dontsov.repairService.model.MalfunctionType;
import com.dontsov.repairService.service.MalfunctionTypeService;
import com.dontsov.repairService.service.impl.MalfunctionTypeServiceImpl;


public class ShowAddReview implements Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		return "/WEB-INF/view/review/addReview.jsp";
	}

}
