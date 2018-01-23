package com.dontsov.repairService.controller.commands.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dontsov.repairService.controller.commands.Command;
import com.dontsov.repairService.model.MalfunctionType;
import com.dontsov.repairService.model.User;
import com.dontsov.repairService.model.User.Role;
import com.dontsov.repairService.service.MalfunctionTypeService;
import com.dontsov.repairService.service.UserService;
import com.dontsov.repairService.service.impl.MalfunctionTypeServiceImpl;
import com.dontsov.repairService.service.impl.UserServiceImpl;


public class AddUser implements Command {

	private static final String SURNAME = "surname";
	private static final String NAME = "name";
	private static final String SECONDNAME = "secondName";
	private static final String EMAIL = "email";
	private static final String PHONE = "phone";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private static final String PASSWORD_CONFIRM = "passwordConfirm";


	private UserService userService;

	public AddUser() {
		this.userService = UserServiceImpl.getInstance();
	}
	public AddUser(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String surname = request.getParameter(SURNAME);
		String name = request.getParameter(NAME);
		String secondName = request.getParameter(SECONDNAME);
		String email = request.getParameter(EMAIL);
		String phone = request.getParameter(PHONE);
		String username = request.getParameter(USERNAME);
		String password = request.getParameter(PASSWORD);
		String passwordConfirm = request.getParameter(PASSWORD_CONFIRM);
		
		//TODO Data validation
		//TODO Check for username and email unique
		//TODO check for password confirm
		
		User user = new User.Builder()
				.setSurname(surname)
				.setName(name)
				.setSecondName(secondName)
				.setEmail(email)
				.setPhone(phone)
				.setUsername(username)
				.setPassword(password)
				.setRole(User.Role.USER)
				.build();
		
		userService.saveUser(user);
		return "/WEB-INF/view/home.jsp";
	}

}
