package com.dontsov.repairService.service;

import java.util.List;
import java.util.Optional;

import com.dontsov.repairService.model.User;

public interface UserService {

	public void saveUser (User user);
	
	public void deleteUser (int id);
	
	public List<User> getUsers();
	
	public Optional<User> getUser(int id);
	
	public Optional<User> login(String username, String password);
	

}
