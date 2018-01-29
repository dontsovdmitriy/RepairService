package com.dontsov.repairService.dao;

import java.util.List;
import java.util.Optional;

import com.dontsov.repairService.model.User;
import com.dontsov.repairService.model.User.Role;

public interface UserDAO extends GenericDAO<User>{

	/**
	 * Retrieves an user by his username.
	 */
	Optional<User> findByUsername(String username);

	/**
	 * Change role for user.
	 */
	void changeRole(User user);

	/**
	 * Return  users for Role
	 */
	List<User> getUserByRole(Role role);
	
	/**
	 * Returns true if a user with this email is already contained in the database
	 *
	 * @param periodical email
	 */	
	boolean emailExistInDB(String email);
	
	/**
	 * Returns true if a user with this login is already contained in the database
	 *
	 * @param periodical login
	 */
	boolean usernameExistInDB(String username);

	/**
	 * Return list of user for viewing with pagination
	 */
	List<User> getUsersPag(int offset);

	/**
	 * Return amount of users in DB
	 */
	int getUsersAmmount();

}
