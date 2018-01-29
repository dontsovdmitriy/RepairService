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

	public void changeRole(User user);

	public List<User> getUserByRole(User.Role role);

	public boolean emailExistInDB(String email);

	public boolean usernameExistInDB(String email);

	public List<User> getUsersPag(int offset);

	public int getUsersAmmount();

	

}
