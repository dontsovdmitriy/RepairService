package com.dontsov.repairService.controller.validation;

public class InputCheckingServiceImpl implements InputCheckingService{

	public static final String NAME_REGEX = "^[^<>!@#$%&?*(){}:;\\[\\]\\\\/+*]{1,45}$";
	public static final String DESCRIPTION_REGEX = "^[^<>]{1,1000}$";
	public static final String NUMBER_DAY = "\\d{1,3}";
	public static final String MONEY_REGEX = "[0-9]+([,.][0-9]{1,2})?";
	public static final String PHONE_NUMBER_REGEX = "^[\\d]{10}$";
	//TODO продумать регулярку по email
	public static final String EMAIL_REGEX = "([a-zа-я0-9_\\.]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";
	
	@Override
	public boolean checkName(String name) {
		return name.matches(NAME_REGEX);
	}

	@Override
	public boolean checkEmail(String email) {
		return email.matches(EMAIL_REGEX);
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

}
