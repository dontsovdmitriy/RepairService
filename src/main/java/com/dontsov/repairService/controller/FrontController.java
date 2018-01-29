
package com.dontsov.repairService.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.dontsov.repairService.controller.commands.Command;
import com.dontsov.repairService.controller.commands.application.AddApplication;
import com.dontsov.repairService.controller.commands.application.AllApplications;
import com.dontsov.repairService.controller.commands.application.DeleteApplication;
import com.dontsov.repairService.controller.commands.application.MyApplications;
import com.dontsov.repairService.controller.commands.application.ShowAddApplication;
import com.dontsov.repairService.controller.commands.application.ShowDeleteApplication;
import com.dontsov.repairService.controller.commands.application.ShowSelectManagerApplication;
import com.dontsov.repairService.controller.commands.application.ShowSelectMasterApplication;
import com.dontsov.repairService.controller.commands.application.ShowUpdateManagerApplication;
import com.dontsov.repairService.controller.commands.application.ShowUpdateMasterApplication;
import com.dontsov.repairService.controller.commands.application.UpdateManagerApplication;
import com.dontsov.repairService.controller.commands.application.UpdateMasterApplication;
import com.dontsov.repairService.controller.commands.malfunctionType.AddMalfunctionType;
import com.dontsov.repairService.controller.commands.malfunctionType.AllMalfunctionTypes;
import com.dontsov.repairService.controller.commands.malfunctionType.DeleteMalfunctionType;
import com.dontsov.repairService.controller.commands.malfunctionType.ShowAddMalfunctionType;
import com.dontsov.repairService.controller.commands.malfunctionType.ShowDeleteMalfunctionType;
import com.dontsov.repairService.controller.commands.review.AddReview;
import com.dontsov.repairService.controller.commands.review.AllReviews;
import com.dontsov.repairService.controller.commands.review.DeleteReview;
import com.dontsov.repairService.controller.commands.review.ShowAddReview;
import com.dontsov.repairService.controller.commands.review.ShowDeleteReview;
import com.dontsov.repairService.controller.commands.user.AddUser;
import com.dontsov.repairService.controller.commands.user.AllUsers;
import com.dontsov.repairService.controller.commands.user.ChangeRole;
import com.dontsov.repairService.controller.commands.user.DeleteUser;
import com.dontsov.repairService.controller.commands.user.Login;
import com.dontsov.repairService.controller.commands.user.Logout;
import com.dontsov.repairService.controller.commands.user.ShowAddUser;
import com.dontsov.repairService.controller.commands.user.ShowChangeRole;
import com.dontsov.repairService.controller.commands.user.ShowDeleteUser;
import com.dontsov.repairService.controller.commands.user.ShowHome;
import com.dontsov.repairService.controller.commands.user.ShowLogin;


/**
 * The class describes the class for the creation of {@code Command} objects.
 */
public class FrontController extends HttpServlet {

	private static final Logger LOGGER = Logger.getLogger(FrontController.class);

	/**
	 * Hash map of all available commands.
	 */
	private Map<String , Command> commands = new HashMap<>();

	public FrontController() {
		super();
	}

	@Override
	public void init(){

		//Malfunction type
		commands.put("POST:/addMalfunctionType", new AddMalfunctionType());
		commands.put("GET:/showAddMalfunctionType", new ShowAddMalfunctionType());
		commands.put("GET:/allMalfunctionTypes", new AllMalfunctionTypes());
		commands.put("GET:/showDeleteMalfunctionType", new ShowDeleteMalfunctionType());
		commands.put("POST:/deleteMalfunctionType", new DeleteMalfunctionType());

		//User
		commands.put("GET:/showAddUser", new ShowAddUser());
		commands.put("POST:/addUser", new AddUser());
		commands.put("GET:/showDeleteUser", new ShowDeleteUser());
		commands.put("POST:/deleteUser", new DeleteUser());
		commands.put("GET:/allUsers", new AllUsers());
		commands.put("POST:/allUsers", new AllUsers());
		commands.put("GET:/showLogin", new ShowLogin());
		commands.put("POST:/login", new Login());
		commands.put("GET:/logout", new Logout());
		commands.put("GET:/showChangeRole", new ShowChangeRole());
		commands.put("POST:/changeUserRole", new ChangeRole());
		commands.put("GET:/showHome", new ShowHome());

		//Review
		commands.put("POST:/addReview", new AddReview());
		commands.put("GET:/showAddReview", new ShowAddReview());
		commands.put("GET:/allReviews", new AllReviews());
		commands.put("POST:/allReviews", new AllReviews());
		commands.put("GET:/showDeleteReview", new ShowDeleteReview());
		commands.put("POST:/deleteReview", new DeleteReview());

		//Application
		commands.put("POST:/addApplication", new AddApplication());
		commands.put("GET:/showAddApplication", new ShowAddApplication());
		commands.put("GET:/allApplications", new AllApplications());
		commands.put("POST:/allApplications", new AllApplications());
		commands.put("GET:/showDeleteApplication", new ShowDeleteApplication());
		commands.put("POST:/deleteApplication", new DeleteApplication());
		commands.put("GET:/showSelectManagerApplication", new ShowSelectManagerApplication());
		commands.put("POST:/showUpdateManagerApplication", new ShowUpdateManagerApplication());
		commands.put("POST:/updateManagerApplication", new UpdateManagerApplication());
		commands.put("GET:/showSelectMasterApplication", new ShowSelectMasterApplication());
		commands.put("POST:/showUpdateMasterApplication", new ShowUpdateMasterApplication());
		commands.put("POST:/updateMasterApplication", new UpdateMasterApplication());
		commands.put("GET:/myApplications", new MyApplications());
		commands.put("POST:/myApplications", new MyApplications());
	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request , response);
	}

	/**
	 * The method performs some action depending on the {@code Command} object.
	 *
	 * @param request http servlet request.
	 * @param response http servlet response.
	 * @return the string that describes the path to the corresponding jsp page.
	 */
	void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String method = request.getMethod().toUpperCase();
		String path = request.getRequestURI();
		path = path.replaceAll(".*/pages", "").replaceAll("\\d+", "");
		String key = method+":"+path;
		Command command = commands.getOrDefault(key, (req , resp)->"/index.jsp" ); 
		String viewPage = command.execute(request, response);
		
		LOGGER.info("FrontController forward to " + viewPage);

		request.getRequestDispatcher(viewPage).forward(request, response);
	}
}
