package com.dontsov.repairService.controller.commands.application;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dontsov.repairService.controller.commands.Command;
import com.dontsov.repairService.model.Application;
import com.dontsov.repairService.model.MalfunctionType;
import com.dontsov.repairService.model.Review;
import com.dontsov.repairService.model.User;
import com.dontsov.repairService.model.Application.ApplicationStatus;
import com.dontsov.repairService.service.ApplicationService;
import com.dontsov.repairService.service.MalfunctionTypeService;
import com.dontsov.repairService.service.ReviewService;
import com.dontsov.repairService.service.UserService;
import com.dontsov.repairService.service.impl.ApplicationServiceImpl;
import com.dontsov.repairService.service.impl.MalfunctionTypeServiceImpl;
import com.dontsov.repairService.service.impl.ReviewServiceImpl;
import com.dontsov.repairService.service.impl.UserServiceImpl;


public class AddApplication implements Command {

	private static final String MALFUNCTION_TYPE = "malfunctionType";
	private static final String DESCRIPTION = "description";

	private ApplicationService applicationService;
	private MalfunctionTypeService malfunctionTypeService;

	public AddApplication() {
		this.applicationService = ApplicationServiceImpl.getInstance();
		this.malfunctionTypeService = MalfunctionTypeServiceImpl.getInstance();
	}
	public AddApplication(ApplicationService applicationService, MalfunctionTypeService malfunctionTypeService) {
		this.applicationService = applicationService;
		this.malfunctionTypeService = malfunctionTypeService;

	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String malfunctionTypeReq = request.getParameter(MALFUNCTION_TYPE);
		String description = request.getParameter(DESCRIPTION);

		User user = (User) session.getAttribute("user");
		
		MalfunctionType malfunctionType = new MalfunctionType.Builder()
				.setId(Integer.parseInt(malfunctionTypeReq))
				.setRepairDay(malfunctionTypeService.getMalfunctionType(Integer.parseInt(malfunctionTypeReq)).get().getRepairDay())
				.build();
		
		//TODO Data validation
		
		Application application = new Application.Builder()
				.setCreationDate(LocalDate.now())
				.setMalfunctionType(malfunctionType)
				.setStatus(ApplicationStatus.TODO)
				.setDescription(description)
				.setClient(user)
				.setCompletionDate(LocalDate.now().plusDays(malfunctionType.getRepairDay()))
				.build();
		
		applicationService.saveApplication(application);
		return "/WEB-INF/view/home.jsp";
	}

}
