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

		generalURI.add("/RepairService/pages/showAddUser");
		generalURI.add("/RepairService/pages/addUser");
		generalURI.add("/RepairService/pages/showLogin");
		generalURI.add("/RepairService/pages/login");
		generalURI.add("/RepairService/pages/allReviews");
		generalURI.add("/RepairService/pages/allMalfunctionTypes");

		clientURI.addAll(generalURI);
		clientURI.add("/RepairService/pages/logout");
		clientURI.add("/RepairService/pages/addReview");
		clientURI.add("/RepairService/pages/showAddReview");
		clientURI.add("/RepairService/pages/addApplication");
		clientURI.add("/RepairService/pages/showAddApplication");
		clientURI.add("/RepairService/pages/myApplications");
		clientURI.add("/RepairService/pages/showHome");
		
		masterURI.addAll(clientURI);
		masterURI.add("/RepairService/pages/showSelectMasterApplication");
		masterURI.add("/RepairService/pages/showUpdateMasterApplication");
		masterURI.add("/RepairService/pages/updateMasterApplication");
		
		managerURI.addAll(masterURI);
		managerURI.add("/RepairService/pages/addMalfunctionType");
		managerURI.add("/RepairService/pages/showAddMalfunctionType");
		managerURI.add("/RepairService/pages/allUsers");
		managerURI.add("/RepairService/pages/allApplications");
		managerURI.add("/RepairService/pages/showDeleteApplication");
		managerURI.add("/RepairService/pages/deleteApplication");
		managerURI.add("/RepairService/pages/showSelectManagerApplication");
		managerURI.add("/RepairService/pages/showUpdateManagerApplication");
		managerURI.add("/RepairService/pages/updateManagerApplication");
		managerURI.add("/RepairService/pages/addMalfunctionType");

		adminURI.addAll(managerURI);
		adminURI.add("/RepairService/pages/showDeleteReview");
		adminURI.add("/RepairService/pages/deleteReview");
		adminURI.add("/RepairService/pages/showDeleteMalfunctionType");
		adminURI.add("/RepairService/pages/deleteMalfunctionType");
		adminURI.add("/RepairService/pages/showDeleteUser");
		adminURI.add("/RepairService/pages/deleteUser");
		adminURI.add("/RepairService/pages/showChangeRole");
		adminURI.add("/RepairService/pages/changeUserRole");

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
