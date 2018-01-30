package com.dontsov.repairService.controller.validation;

import java.util.regex.Pattern;

public class InputCheckingServiceImpl implements InputCheckingService{

	public static final String NAME_REGEX = "^[^<>!@#$%&?*(){}:;\\[\\]\\\\/+*]{1,45}$";
	public static final String TYPE_REGEX = "^[^<>#$&*{}\\[\\]\\\\/+*]{1,45}$";
	public static final String DESCRIPTION_REGEX = "^[^<>]{1,1000}$";
	public static final String NUMBER_DAY = "\\d{1,3}";
	public static final String MONEY_REGEX = "[0-9]+([,.]{1})+([0-9]{2})";
//	public static final String MONEY_REGEX = "[0-9]+([,.][0-9]{1,2})?";

	public static final String PHONE_NUMBER_REGEX = "^[\\d]{10}$";
	public static final String EMAIL_REGEX = "([A-Za-zА-Яа-я0-9_\\.]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	@Override
	public boolean checkName(String name) {
		return name.matches(NAME_REGEX);
	}

	@Override
	public boolean checkEmail(String email) {
		return (VALID_EMAIL_ADDRESS_REGEX.matcher(email)).find();
	}

	@Override
	public boolean checkPhone(String phone) {
		return phone.matches(PHONE_NUMBER_REGEX);
	}

	@Override
	public boolean checkDescription(String description) {
		return description.matches(DESCRIPTION_REGEX);
	}

	@Override
	public boolean checkPrice(String price) {
		return price.matches(MONEY_REGEX);
	}

	@Override
	public boolean checkRepairDay(String repairDay) {
		return repairDay.matches(NUMBER_DAY);
	}

	@Override
	public boolean checkType(String type) {
		return type.matches(TYPE_REGEX);
	}

}
