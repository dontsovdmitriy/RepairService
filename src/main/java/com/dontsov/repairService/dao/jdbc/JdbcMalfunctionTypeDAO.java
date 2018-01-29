package com.dontsov.repairService.dao.jdbc;

import java.sql.*;
import java.util.*;

import org.apache.log4j.Logger;

import com.dontsov.repairService.dao.*;
import com.dontsov.repairService.model.MalfunctionType;

public class JdbcMalfunctionTypeDAO implements MalfunctionTypeDAO {

	private static final Logger LOGGER = Logger.getLogger(JdbcMalfunctionTypeDAO.class);

	private static final String INSERT_MALFUNCTION_TYPE = "INSERT INTO malfunction_type " + "(type, repair_day) " + "VALUES (?, ?)";
	private static final String SELECT_ALL_MALFUNCTION_TYPE = "SELECT * FROM malfunction_type";
	private static final String DELETE_MALFUNCTION_TYPE = "DELETE FROM malfunction_type WHERE id = ?";
	private static final String SELECT_FROM_MALFUNCTION_TYPE_BY_ID = "SELECT * FROM malfunction_type WHERE id = ?";

	private Connection connection;

	JdbcMalfunctionTypeDAO(Connection connection) {
		this.connection = connection;
	}

	public List<MalfunctionType> getAll() {
		List<MalfunctionType> resultList = new ArrayList<>();
		try (PreparedStatement ps = connection.prepareStatement(SELECT_ALL_MALFUNCTION_TYPE)) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				resultList.add(UtilDAO.createMalfunctionType(rs));
			}

		} catch (SQLException e) {
			String message = String.format("Exception during find all malfunction types");
			LOGGER.error( message , e);
			throw new RuntimeException(message, e);
		}
		return resultList;
	}

	public void save(MalfunctionType malfunctionTypealfunctionType) {
		try (PreparedStatement ps = connection.prepareStatement(INSERT_MALFUNCTION_TYPE )){
			
			ps.setString(1, malfunctionTypealfunctionType.getType());
			ps.setInt(2, malfunctionTypealfunctionType.getRepairDay());
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			String message = String.format("Exception during add a malfunctionType with type = %s", malfunctionTypealfunctionType.getType());
			LOGGER.error( message , e);
			throw new RuntimeException(message, e);
		}

	}

	public Optional<MalfunctionType> get(int id) {
		Optional<MalfunctionType> result = Optional.empty();
		try (PreparedStatement ps = connection.prepareStatement(SELECT_FROM_MALFUNCTION_TYPE_BY_ID)) {
			
			ps.setLong( 1 , id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				result = Optional.of(UtilDAO.createMalfunctionType(rs));
			}
			
		} catch (SQLException e) {
			String message = String.format("Exception during find  malfunction type with id= " + id);
			LOGGER.error( message , e);
			throw new RuntimeException(message, e);
		}
		return result;
	}

	public void delete(int id) {
		try (PreparedStatement ps = connection.prepareStatement(DELETE_MALFUNCTION_TYPE)){
			
			ps.setInt(1, id);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			String message = String.format("Exception during delete a malfunctionType with id = %s", id);
			LOGGER.error( message , e);
			throw new RuntimeException(message, e);
		}
	}

}
