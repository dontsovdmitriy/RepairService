package com.dontsov.repairService.controller.commands.user;

import java.util.Optional;

import javax.servlet.http.*;

import org.apache.log4j.Logger;

import com.dontsov.repairService.controller.commands.Command;
import com.dontsov.repairService.model.User;
import com.dontsov.repairService.service.UserService;
import com.dontsov.repairService.service.impl.UserServiceImpl;


/**
 * The class describes the {@code Command} interface implementation.
 * It contains a method for user login
 */
public class Login implements Command {

//	private static final String PAGE_TO_GO = "/WEB-INF/view/home.jsp";

	private static final String PARAM_USERNAME = "username";
	private static final String PARAM_PASSWORD = "password";
	
	private static final Logger logger = Logger.getLogger(Login.class);

	private UserService userService;

	public Login() {
		this.userService = UserServiceImpl.getInstance();
	}
	public Login(UserService userService) {
		this.userService = userService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
	{
		String username = request.getParameter(PARAM_USERNAME);
		String password = request.getParameter(PARAM_PASSWORD);

		//TODO validation
/*		
		if (email == null || password == null ) {
			request.setAttribute("message", NULL_ERROR);
			logger.error("Errors occurred User with email " + email + ". " + "The login data contains blank values");
			return PAGE_TO_GO;
		}

		if(!checkingService.checkLoginForm(email, password)){
			request.setAttribute("message", REGULAR_EXP_CONFIRM_ERROR);
			logger.error("Errors occurred User with email " + email + ". " + "The login data was not entered correctly");
			return PAGE_TO_GO;
		}
*/
		Optional<User> optionalUser = userService.login(username, password);

		if (!optionalUser.isPresent()) {
			logger.error("Errors occurred User with username " + username + ". " + "User login unsuccessful");
			return "/WEB-INF/view/home.jsp";
		} else {		
			User user = optionalUser.get();
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			logger.info("User with username " + username +  "User login successful");
			return "/WEB-INF/view/home.jsp";
		}
	}
}



