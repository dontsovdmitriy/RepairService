package com.dontsov.repairService.service;

import java.util.List;
import java.util.Optional;

import com.dontsov.repairService.model.User;

public interface UserService {

	/**
     * Method add user
     */
	public void saveUser (User user);
	
	/**
     * Method delete user by id
     */
	public void deleteUser (int id);
	
	/**
     * Method return list of all users
     */
	public List<User> getUsers();
	
	/**
     * Method return user by id
     */
	public Optional<User> getUser(int id);
	
	/**
     *	Method for entering the user into the application
     */
	public Optional<User> login(String username, String password);

	/**
     *	Method change user role
     */
	public void changeRole(User user);

	/**
     *	Method return list of users by role
     */
	public List<User> getUserByRole(User.Role role);

	/**
     *	Method check if email exists in DB
     */
	public boolean emailExistInDB(String email);

	/**
     *	Method check if username exists in DB
     */
	public boolean usernameExistInDB(String email);

	/**
     *	Method return list of users for pagination view
     */
	public List<User> getUsersPag(int offset);

	/**
     *	Method return amount of users in DB
     */
	public int getUsersAmmount();

	

}
