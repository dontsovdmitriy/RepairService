package com.dontsov.repairService.dao;

import java.util.Optional;

import com.dontsov.repairService.model.User;

public interface UserDAO extends GenericDAO<User>{

	/**
	 * Returns true if a user with this email is already contained in the database
	 *
	 * @param periodical email
	 */
	boolean emailExistsInDb(String email);
	
	/**
	 * Returns true if a user with this login is already contained in the database
	 *
	 * @param periodical login
	 */
	boolean usernameExistsInDb(String username);

	/**
	 * Retrieves an user by his email.
	 *
	 * @param id must not be null
	 * @return object of the class Optional <User>
	 */
	Optional<User> findByUsername(String username);

}
