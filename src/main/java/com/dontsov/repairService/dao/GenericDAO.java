package com.dontsov.repairService.dao;

import java.util.List;
import java.util.Optional;


public interface GenericDAO<E> {

	public List<E> getAll();

	public void save(E e);

	public Optional<E> get(int id);

	public void delete(int id);
}
