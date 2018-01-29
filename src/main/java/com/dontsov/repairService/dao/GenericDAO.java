package com.dontsov.repairService.dao;

import java.util.List;
import java.util.Optional;


public interface GenericDAO<E> {

	 /**
     * Returns all entities from the db.
     */
	public List<E> getAll();

	/**
     * Creates a new entity taking values for the fields from the passed entity.
     */
	public void save(E e);

	/**
     * Retrieves an entity by its id.
     */
	public Optional<E> get(int id);

	/**
     * Delete an entity by its id.
     */
	public void delete(int id);
}
