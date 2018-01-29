package com.dontsov.repairService.controller.commands.user;

import java.util.Optional;

import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.dontsov.repairService.controller.commands.Command;
import com.dontsov.repairService.controller.validation.*;
import com.dontsov.repairService.model.User;
import com.dontsov.repairService.service.UserService;
import com.dontsov.repairService.service.impl.UserServiceImpl;


/**
 * The class describes the {@code Command} interface implementation.
 * It contains a method for user login
 */
public class Login implements Command {

	private static final String PARAM_USERNAME = "username";
	private static final String PARAM_PASSWORD = "password";
	
	private static final String REGEX_EXCEP_USERNAME = "exception.username";
	private static final String REGEX_EXCEP_PASSWORD = "exception.password";

	private static final String EXCEP_LOGIN_PASSWORD = "exception.incorrectLoginOrPassword";

	private static final String VALIDATION_EXCEPTION_PAGE = "/WEB-INF/view/exceptionPage.jsp";
	private static final String SUCCESSFUL_PAGE = "/WEB-INF/view/home.jsp";

	private static final Logger LOGGER = Logger.getLogger(Login.class);


	private UserService userService;
	private InputCheckingService checkingService;


	public Login() {
		this.userService = UserServiceImpl.getInstance();
		this.checkingService = new InputCheckingServiceImpl();

	}
	public Login(UserService userService, InputCheckingService checkingService) {
		this.userService = userService;
		this.checkingService = checkingService;

	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
	{
		String username = request.getParameter(PARAM_USERNAME);
		String password = request.getParameter(PARAM_PASSWORD);

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
		
		Optional<User> optionalUser = userService.login(username, password);

		if (!optionalUser.isPresent()) {
			request.setAttribute("message", EXCEP_LOGIN_PASSWORD);
			LOGGER.info(EXCEP_LOGIN_PASSWORD);
			return VALIDATION_EXCEPTION_PAGE;
		} else {		
			User user = optionalUser.get();
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			return SUCCESSFUL_PAGE;
		}
	}
}



