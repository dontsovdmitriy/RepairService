package com.dontsov.repairService.service.impl;

import java.util.List;
import java.util.Optional;

import com.dontsov.repairService.dao.DaoConnection;
import com.dontsov.repairService.dao.DaoFactory;
import com.dontsov.repairService.dao.MalfunctionTypeDAO;
import com.dontsov.repairService.model.MalfunctionType;
import com.dontsov.repairService.service.MalfunctionTypeService;

public class MalfunctionTypeServiceImpl implements MalfunctionTypeService {

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

	public List<MalfunctionType> getMalfunctionTypes() {
		try(DaoConnection connection = daoFactory.getConnection()){
			MalfunctionTypeDAO malfunctionTypeDao = daoFactory.createMalfunctionTypeDAO(connection);
		//	logger.info("Searching invoice with subscription id = " + subscription.getId());
			return malfunctionTypeDao.getAll();
		}
	}

	public void saveMalfunctionType(MalfunctionType malfunctionType) {
		try(DaoConnection connection = daoFactory.getConnection()){
			MalfunctionTypeDAO malfunctionTypeDao = daoFactory.createMalfunctionTypeDAO(connection);
		//	logger.info("Searching invoice with subscription id = " + subscription.getId());
			malfunctionTypeDao.save(malfunctionType);
		}
	}

	public Optional<MalfunctionType> getMalfunctionType(int id) {
		try(DaoConnection connection = daoFactory.getConnection()){
			MalfunctionTypeDAO malfunctionTypeDao = daoFactory.createMalfunctionTypeDAO(connection);
		//	logger.info("Searching invoice with subscription id = " + subscription.getId());
			return malfunctionTypeDao.get(id);
		}
	}

	public void deleteMalfunctionType(int id) {
		try(DaoConnection connection = daoFactory.getConnection()){
			MalfunctionTypeDAO malfunctionTypeDao = daoFactory.createMalfunctionTypeDAO(connection);
		//	logger.info("Searching invoice with subscription id = " + subscription.getId());
			malfunctionTypeDao.delete(id);
		}

	}

}
