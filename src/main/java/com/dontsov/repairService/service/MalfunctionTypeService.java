package com.dontsov.repairService.service;

import java.util.List;
import java.util.Optional;

import com.dontsov.repairService.model.MalfunctionType;

public interface MalfunctionTypeService {

	public List<MalfunctionType> getMalfunctionTypes();

	public void saveMalfunctionType(MalfunctionType malfunctionType);

	public Optional<MalfunctionType> getMalfunctionType(int id);

	public void deleteMalfunctionType(int theId);
	
	//TODO добавить UPDATE
}
