package com.dontsov.repairService.dao.jdbc;

import java.sql.SQLException;

import javax.naming.*;
import javax.sql.DataSource;

import com.dontsov.repairService.dao.ApplicationDAO;
import com.dontsov.repairService.dao.DaoConnection;
import com.dontsov.repairService.dao.DaoFactory;
import com.dontsov.repairService.dao.MalfunctionTypeDAO;
import com.dontsov.repairService.dao.ReviewDAO;
import com.dontsov.repairService.dao.UserDAO;

import java.sql.*;

public class JdbcDaoFactory extends DaoFactory {

//	private static final Logger logger = Logger.getLogger(JdbcDaoFactory.class);
	private static DataSource dataSource;

	public JdbcDaoFactory() {
		try {
			InitialContext ic = new InitialContext();
			dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/repair_service"); 
		} catch (NamingException e) {
	//		logger.error("Error in looking up the data source: ", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public DaoConnection getConnection() {
		try {
			return new JdbcDaoConnection(dataSource.getConnection());
		} catch (SQLException e) {
	//		logger.error("Error during the DaoConnection getting: ", e);
			throw new RuntimeException(e);
		}
	}



	@Override
	public ApplicationDAO createApplicationDAO(DaoConnection connection) {
		JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
		Connection sqlConnection = jdbcConnection.getConnection();
		return new JdbcApplicationDAO(sqlConnection);
	}

	@Override
	public MalfunctionTypeDAO createMalfunctionTypeDAO(DaoConnection connection) {
		JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
		Connection sqlConnection = jdbcConnection.getConnection();
		return new JdbcMalfunctionTypeDAO(sqlConnection);
	}

	@Override
	public ReviewDAO createReviewDAO(DaoConnection connection) {
		JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
		Connection sqlConnection = jdbcConnection.getConnection();
		return new JdbcReviewDAO(sqlConnection);
	}

	@Override
	public UserDAO createUserDAO(DaoConnection connection) {
		JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) connection;
		Connection sqlConnection = jdbcConnection.getConnection();
		return new JdbcUserDAO(sqlConnection);
	}

}
