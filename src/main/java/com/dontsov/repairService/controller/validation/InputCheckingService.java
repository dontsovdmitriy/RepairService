package com.dontsov.repairService.controller.validation;

/**
 * The class contains a methods for checking input values on regular expression
 */
public interface InputCheckingService {

	/**
	 * Method check user registration form on regular expression
	 * @param user for checking
	 * @return true if checking is successful
	 */
	boolean checkName(String name);
	
	boolean checkType(String type);

	boolean checkEmail(String email);

	boolean checkPhone(String phone);

	boolean checkDescription(String description);

	boolean checkPrice(String price);

	boolean checkRepairDay(String repairDay);

}
