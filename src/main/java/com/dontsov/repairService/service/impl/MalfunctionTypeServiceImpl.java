package com.dontsov.repairService.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.dontsov.repairService.dao.*;
import com.dontsov.repairService.model.MalfunctionType;
import com.dontsov.repairService.service.MalfunctionTypeService;

public class MalfunctionTypeServiceImpl implements MalfunctionTypeService {

	private static final Logger LOGGER = Logger.getLogger(MalfunctionTypeServiceImpl.class);

	private DaoFactory daoFactory;

	private static class Holder{
		static final MalfunctionTypeService INSTANCE = new MalfunctionTypeServiceImpl( DaoFactory.getInstance() ); 
	}

	private MalfunctionTypeServiceImpl(DaoFactory instance) {
		this.daoFactory = instance;
	}

	public static MalfunctionTypeService getInstance(){
		return Holder.INSTANCE;
	}
	
	@Override
	public List<MalfunctionType> getMalfunctionTypes() {
		try(DaoConnection connection = daoFactory.getConnection()){
			MalfunctionTypeDAO malfunctionTypeDao = daoFactory.createMalfunctionTypeDAO(connection);
			LOGGER.info("Getting all malfunctions types");
			return malfunctionTypeDao.getAll();
		}
	}

	@Override
	public void saveMalfunctionType(MalfunctionType malfunctionType) {
		try(DaoConnection connection = daoFactory.getConnection()){
			MalfunctionTypeDAO malfunctionTypeDao = daoFactory.createMalfunctionTypeDAO(connection);
			LOGGER.info("Save malfunction type with id=" + malfunctionType.getId());
			malfunctionTypeDao.save(malfunctionType);
		}
	}

	@Override
	public Optional<MalfunctionType> getMalfunctionType(int id) {
		try(DaoConnection connection = daoFactory.getConnection()){
			MalfunctionTypeDAO malfunctionTypeDao = daoFactory.createMalfunctionTypeDAO(connection);
			LOGGER.info("Get malfunction type with id = " + id);
			return malfunctionTypeDao.get(id);
		}
	}

	@Override
	public void deleteMalfunctionType(int id) {
		try(DaoConnection connection = daoFactory.getConnection()){
			MalfunctionTypeDAO malfunctionTypeDao = daoFactory.createMalfunctionTypeDAO(connection);
			LOGGER.info("Delete malfunction type with id = " + id);
			malfunctionTypeDao.delete(id);
		}

	}
}
