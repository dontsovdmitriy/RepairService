package com.dontsov.repairService.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.dontsov.repairService.dao.DaoConnection;
import com.dontsov.repairService.dao.DaoFactory;
import com.dontsov.repairService.dao.MalfunctionTypeDAO;
import com.dontsov.repairService.dao.UtilDAO;
import com.dontsov.repairService.model.MalfunctionType;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UtilDAO.class)
@PowerMockIgnore("javax.management.*")
public class MalfunctionTypeServiceImplTest {

	@Mock
	private DaoFactory daoFactory;
	@Mock
	private DaoConnection connection;
	@Mock
	private MalfunctionTypeDAO malfunctionTypeDAO;
	@InjectMocks
	private MalfunctionTypeServiceImpl malfunctionTypeServiceImpl;
	
	@Test
	public void getMalfunctionTypes_test() {
		List<MalfunctionType> malfunctionTypes = new ArrayList<>();
		when(daoFactory.getConnection()).thenReturn(connection);
		when(daoFactory.createMalfunctionTypeDAO(connection)).thenReturn(malfunctionTypeDAO);
		when(malfunctionTypeDAO.getAll()).thenReturn(malfunctionTypes);      
		List<MalfunctionType> malfunctionTypeResult = malfunctionTypeServiceImpl.getMalfunctionTypes();
		verify(daoFactory).getConnection();
		verify(daoFactory).createMalfunctionTypeDAO(connection);
		verify(malfunctionTypeDAO).getAll();
		assertEquals(malfunctionTypes, malfunctionTypeResult);	
	}
	
	@Test
	public void getMalfunctionType_test() {
		MalfunctionType malfunctionType = new MalfunctionType();
		Optional<MalfunctionType> malfunctionTypeOptional = Optional.of(malfunctionType);
		when(daoFactory.getConnection()).thenReturn(connection);
		when(daoFactory.createMalfunctionTypeDAO(connection)).thenReturn(malfunctionTypeDAO);
		when(malfunctionTypeDAO.get(1)).thenReturn(malfunctionTypeOptional);      
		Optional<MalfunctionType> malfunctionTypeResult = malfunctionTypeServiceImpl.getMalfunctionType(1);
		verify(daoFactory).getConnection();
		verify(daoFactory).createMalfunctionTypeDAO(connection);
		verify(malfunctionTypeDAO).get(1);
		assertEquals(malfunctionTypeOptional, malfunctionTypeResult);	
	}
	

}
