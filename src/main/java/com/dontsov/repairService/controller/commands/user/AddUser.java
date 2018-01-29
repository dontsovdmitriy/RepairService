package com.dontsov.repairService.controller.commands.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.dontsov.repairService.controller.commands.Command;
import com.dontsov.repairService.controller.validation.InputCheckingService;
import com.dontsov.repairService.controller.validation.InputCheckingServiceImpl;
import com.dontsov.repairService.model.User;
import com.dontsov.repairService.service.UserService;
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

	private static final String REGEX_EXCEP_SURNAME = "exception.surname";
	private static final String REGEX_EXCEP_NAME = "exception.name";
	private static final String REGEX_EXCEP_SECOND_NAME = "exception.secondName";
	private static final String REGEX_EXCEP_EMAIL = "exception.email";
	private static final String REGEX_EXCEP_PHONE = "exception.email";
	private static final String REGEX_EXCEP_USERNAME = "exception.username";
	private static final String REGEX_EXCEP_PASSWORD = "exception.password";
	private static final String REGEX_EXCEP_CONFIRM_PASSWORD = "confirmPassword";
	private static final String EMAIL_ALREADY_ASSIGNED = "exception.emailAllreadyAssigned";
	private static final String USERNAME_ALREADY_ASSIGNED = "exception.usernameAllreadyAssigned";
	private static final String PASSWORD_DOESNT_CONFIRM = "exception.passwordConfirm";

	private static final String VALIDATION_EXCEPTION_PAGE = "/WEB-INF/view/exceptionPage.jsp";
	private static final String SUCCESSFUL_PAGE = "/WEB-INF/view/home.jsp";

	private static final Logger LOGGER = Logger.getLogger(AddUser.class);

	private UserService userService;
	private InputCheckingService checkingService;

	public AddUser() {
		this.userService = UserServiceImpl.getInstance();
		this.checkingService = new InputCheckingServiceImpl();

	}
	public AddUser(UserService userService, InputCheckingService checkingService) {
		this.userService = userService;
		this.checkingService = checkingService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String surname = request.getParameter(SURNAME);
		String name = request.getParameter(NAME);
		String secondName = request.getParameter(SECONDNAME);
		String email = request.getParameter(EMAIL);
		String phone = request.getParameter(PHONE);
		String username = request.getParameter(USERNAME);
		String password = request.getParameter(PASSWORD);
		String passwordConfirm = request.getParameter(PASSWORD_CONFIRM);
				
		if(!checkingService.checkName(surname)){
			request.setAttribute("message", REGEX_EXCEP_SURNAME);
			LOGGER.info(REGEX_EXCEP_SURNAME);
			return VALIDATION_EXCEPTION_PAGE;
		}
		
		if(!checkingService.checkName(name)){
			request.setAttribute("message", REGEX_EXCEP_NAME);
			LOGGER.info(REGEX_EXCEP_NAME);
			return VALIDATION_EXCEPTION_PAGE;
		}
		
		if(!(secondName == "") && !checkingService.checkName(secondName)){
			request.setAttribute("message", REGEX_EXCEP_SECOND_NAME);
			LOGGER.info(REGEX_EXCEP_SECOND_NAME);
			return VALIDATION_EXCEPTION_PAGE;
		}
		
		if(!checkingService.checkEmail(email)){
			request.setAttribute("message", REGEX_EXCEP_EMAIL);
			LOGGER.info(REGEX_EXCEP_EMAIL);
			return VALIDATION_EXCEPTION_PAGE;
		}
		
		if(!(phone == "") && !checkingService.checkPhone(phone)){
			request.setAttribute("message", REGEX_EXCEP_PHONE);
			LOGGER.info(REGEX_EXCEP_PHONE);
			return VALIDATION_EXCEPTION_PAGE;
		}
		
		if(!checkingService.checkName(username)){
			request.setAttribute("message", REGEX_EXCEP_USERNAME);
			LOGGER.info(REGEX_EXCEP_USERNAME);
			return VALIDATION_EXCEPTION_PAGE;
		}
		
		if(!checkingService.checkName(password)){
			request.setAttribute("message", REGEX_EXCEP_PASSWORD);
			LOGGER.info(REGEX_EXCEP_PASSWORD);
			return VALIDATION_EXCEPTION_PAGE;
		}
		
		if(!checkingService.checkName(passwordConfirm)){
			request.setAttribute("message", REGEX_EXCEP_CONFIRM_PASSWORD);
			LOGGER.info(REGEX_EXCEP_CONFIRM_PASSWORD);
			return VALIDATION_EXCEPTION_PAGE;
		}
		
		if(userService.emailExistInDB(email)) {
			request.setAttribute("message", EMAIL_ALREADY_ASSIGNED);
			LOGGER.info(EMAIL_ALREADY_ASSIGNED);
			return VALIDATION_EXCEPTION_PAGE;
		}
		
		if(userService.usernameExistInDB(username)) {
			request.setAttribute("message", USERNAME_ALREADY_ASSIGNED);
			LOGGER.info(USERNAME_ALREADY_ASSIGNED);
			return VALIDATION_EXCEPTION_PAGE;
		}
		
		if(!password.equals(passwordConfirm)) {
			request.setAttribute("message", PASSWORD_DOESNT_CONFIRM);
			LOGGER.info(PASSWORD_DOESNT_CONFIRM);
			return VALIDATION_EXCEPTION_PAGE;
		}
		
		User user = new User.Builder()
				.setSurname(surname)
				.setName(name)
				.setSecondName(secondName)
				.setEmail(email)
				.setPhone(phone)
				.setUsername(username)
				.setPassword(password)
				.setRole(User.Role.CLIENT)
				.build();
		
		userService.saveUser(user);
		return SUCCESSFUL_PAGE;
	}

}
