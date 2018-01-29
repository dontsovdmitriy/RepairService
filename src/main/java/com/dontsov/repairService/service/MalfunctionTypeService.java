package com.dontsov.repairService.service;

import java.util.List;
import java.util.Optional;

import com.dontsov.repairService.model.MalfunctionType;

/**
 * The {@code MalfunctionTypeService} interface provides methods for {@code MalfunctionType} object.
 */
public interface MalfunctionTypeService {

	/**
     * Method return list of all malfunction types 
     */
	public List<MalfunctionType> getMalfunctionTypes();

	/**
     * Method add new malfunction type
     */
	public void saveMalfunctionType(MalfunctionType malfunctionType);

	/**
     * Method return malfunction type by id
     */
	public Optional<MalfunctionType> getMalfunctionType(int id);

	/**
     * Method delete malfunction type by id
     */
	public void deleteMalfunctionType(int theId);
	
}
