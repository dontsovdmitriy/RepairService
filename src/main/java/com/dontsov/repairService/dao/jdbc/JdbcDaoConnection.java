package com.dontsov.repairService.dao.jdbc;

import java.sql.*;

import com.dontsov.repairService.dao.DaoConnection;

public class JdbcDaoConnection implements DaoConnection {

 //   private static final Logger logger = Logger.getLogger(JdbcDaoConnection.class);
	private Connection connection;
	private boolean inTransaction = false;
	
	JdbcDaoConnection(Connection connection) {
		super();
		this.connection = connection;
	}
	
	@Override
	public void close() {
		if(inTransaction) {
			rollback();
		}
		try {
			connection.close();
		} catch (SQLException e) {
  //          logger.error("Error during connection closing: ", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void begin() {
		try {
			connection.setAutoCommit(false);
			inTransaction = true;
		} catch (SQLException e) {
      //      logger.error("Error during transaction beginning: ", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void commit() {
		try {
			connection.commit();
			inTransaction = false;
		} catch (SQLException e) {
      //      logger.error("Error during transaction commit: ", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void rollback() {
		try {
			connection.rollback();
			inTransaction = false;
		} catch (SQLException e) {
      //      logger.error("Error during transaction rollback: ", e);
			throw new RuntimeException(e);
		}
	}

	Connection getConnection() {
		return connection;
	}
}
