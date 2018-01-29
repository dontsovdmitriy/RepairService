package com.dontsov.repairService.controller.filters;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.dontsov.repairService.model.User;

/**
 * {@code SecurityFilter} is the class that describes security filter.
 * It forbids the access to resources for the users that don't have necessary rights.
 */
public class SecurityFilter implements Filter {
	private static final Logger LOGGER = Logger.getLogger(SecurityFilter.class);

	/**
	 * Set of allowed uri's for all users.
	 */
	private Set<String> generalURI = new HashSet<>();

	/**
	 * A set of allowed uri's for the client who logged
	 */
	private Set<String> clientURI = new HashSet<>();
	
	/**
	 * Set of allowed uri's for the master.
	 */
	private Set<String> masterURI = new HashSet<>();
	
	/**
	 * A set of allowed uri's for the manager who logged
	 */
	private Set<String> managerURI = new HashSet<>();

	/**
	 * Set of allowed uri's for the admin.
	 */
	private Set<String> adminURI = new HashSet<>();

	public void init(FilterConfig fConfig) throws ServletException {

		generalURI.add("/repairService/pages/showAddUser");
		generalURI.add("/repairService/pages/addUser");
		generalURI.add("/repairService/pages/showLogin");
		generalURI.add("/repairService/pages/login");
		generalURI.add("/repairService/pages/allReviews");
		generalURI.add("/repairService/pages/allMalfunctionTypes");

		clientURI.addAll(generalURI);
		clientURI.add("/repairService/pages/logout");
		clientURI.add("/repairService/pages/addReview");
		clientURI.add("/repairService/pages/showAddReview");
		clientURI.add("/repairService/pages/addApplication");
		clientURI.add("/repairService/pages/showAddApplication");
		clientURI.add("/repairService/pages/myApplications");
		clientURI.add("/repairService/pages/showHome");
		
		masterURI.addAll(clientURI);
		masterURI.add("/repairService/pages/showSelectMasterApplication");
		masterURI.add("/repairService/pages/showUpdateMasterApplication");
		masterURI.add("/repairService/pages/updateMasterApplication");
		
		managerURI.addAll(masterURI);
		managerURI.add("/repairService/pages/addMalfunctionType");
		managerURI.add("/repairService/pages/showAddMalfunctionType");
		managerURI.add("/repairService/pages/allUsers");
		managerURI.add("/repairService/pages/allApplications");
		managerURI.add("/repairService/pages/showDeleteApplication");
		managerURI.add("/repairService/pages/deleteApplication");
		managerURI.add("/repairService/pages/showSelectManagerApplication");
		managerURI.add("/repairService/pages/showUpdateManagerApplication");
		managerURI.add("/repairService/pages/updateManagerApplication");
		managerURI.add("/repairService/pages/addMalfunctionType");

		adminURI.addAll(managerURI);
		adminURI.add("/repairService/pages/showDeleteReview");
		adminURI.add("/repairService/pages/deleteReview");
		adminURI.add("/repairService/pages/showDeleteMalfunctionType");
		adminURI.add("/repairService/pages/deleteMalfunctionType");
		adminURI.add("/repairService/pages/showDeleteUser");
		adminURI.add("/repairService/pages/deleteUser");
		adminURI.add("/repairService/pages/showChangeRole");
		adminURI.add("/repairService/pages/changeUserRole");

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String errorString = "exception.security";
		HttpServletRequest requestS = (HttpServletRequest) request;
		HttpServletResponse responseS = (HttpServletResponse) response;
		User user = (User) requestS.getSession().getAttribute("user");
		String uri = requestS.getRequestURI();
		LOGGER.info("encoding: URL " + uri);

		boolean isAllowedGuestAccess =
				(user == null) &&
				generalURI.contains(uri);
		boolean isAllowedClientAccess =
				(user != null) &&
				"CLIENT".equals(user.getRole().name()) &&
				clientURI.contains(uri);
		boolean isAllowedMasterAccess =
				(user != null) &&
				"MASTER".equals(user.getRole().name()) &&
				masterURI.contains(uri);
		boolean isAllowedManagerAccess =
				(user != null) &&
				"MANAGER".equals(user.getRole().name()) &&
				managerURI.contains(uri);
		boolean isAllowedAdminAccess =
				(user != null) &&
				"ADMIN".equals(user.getRole().name()) &&
				adminURI.contains(uri);

		if (isAllowedGuestAccess || isAllowedClientAccess || isAllowedMasterAccess || isAllowedManagerAccess || isAllowedAdminAccess) {
			chain.doFilter(requestS, responseS);
		} else {
			request.setAttribute("message", errorString);
			request.getRequestDispatcher("/WEB-INF/view/exceptionPage.jsp").forward(request,response);
		}
	}

	public void destroy() {
	}
}
