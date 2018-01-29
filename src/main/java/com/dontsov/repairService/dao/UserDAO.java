package com.dontsov.repairService.dao;

import java.util.List;
import java.util.Optional;

import com.dontsov.repairService.model.User;
import com.dontsov.repairService.model.User.Role;

public interface UserDAO extends GenericDAO<User>{

	/**
	 * Retrieves an user by his email.
	 *
	 * @param id must not be null
	 * @return object of the class Optional <User>
	 */
	Optional<User> findByUsername(String username);

	void changeRole(User user);

	List<User> getUserByRole(Role role);
	/**
	 * Returns true if a user with this email is already contained in the database
	 *
	 * @param periodical email
	 */	
	/**
	 * Returns true if a user with this login is already contained in the database
	 *
	 * @param periodical login
	 */
	boolean emailExistInDB(String email);
	
	boolean usernameExistInDB(String username);

	List<User> getUsersPag(int offset);

	int getUsersAmmount();

}
