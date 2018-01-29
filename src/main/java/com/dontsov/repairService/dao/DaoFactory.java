package com.dontsov.repairService.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * {@code DaoFactory} is the abstract class that describes the behavior of the {@code DaoFactory} object.
 */
public abstract class DaoFactory {

	private static final Logger LOGGER = Logger.getLogger(DaoFactory.class);

	/**
	 * The method retrieves {@code DaoConnection} object from the connection pool.
	 *
	 * @return {@code DaoConnection} object.
	 */
	public abstract DaoConnection getConnection();

	/**
	 * The method creates {@code ApplicationDAO} object.
	 *
	 * @param connection {@code DaoConnection} object.
	 * @return created {@code ApplicationDAO} object.
	 */
	public abstract ApplicationDAO createApplicationDAO(DaoConnection connection);

	/**
	 * The method creates {@code MalfunctionTypeDAO} object.
	 *
	 * @param connection {@code DaoConnection} object.
	 * @return created {@code MalfunctionTypeDAO} object.
	 */
	public abstract MalfunctionTypeDAO createMalfunctionTypeDAO(DaoConnection connection);

	/**
	 * The method creates {@code ReviewDAO} object.
	 *
	 * @param connection {@code DaoConnection} object.
	 * @return created {@code ReviewDAO} object.
	 */
	public abstract ReviewDAO createReviewDAO(DaoConnection connection);

	/**
	 * The method creates {@code UserDAO} object.
	 *
	 * @param connection {@code DaoConnection} object.
	 * @return created {@code UserDAO} object.
	 */
	public abstract UserDAO createUserDAO(DaoConnection connection);

	/**
	 * File name with properties for DB
	 */
	public static final String DB_FILE = "/db.properties";

	/**
	 * Factory class name. It should be change depending on which factory you use.
	 */
	private static final String DB_FACTORY_CLASS = "factory.class";

	/**
	 * {@code DaoFactory} instance.
	 */
	private static DaoFactory instance;

	/**
	 * The methods provides creating or getting already created {@code DaoFactory} object.
	 *
	 * @return {@code DaoFactory} object.
	 */
	public static DaoFactory getInstance() {
		if (instance == null) {
			try {
				InputStream inputStream = DaoFactory.class.getResourceAsStream(DB_FILE);
				Properties dbProperties = new Properties();
				dbProperties.load(inputStream);

				instance = (DaoFactory) Class.forName(dbProperties.getProperty(DB_FACTORY_CLASS)).newInstance();

			} catch (IOException | IllegalAccessException |
					ClassNotFoundException | InstantiationException e) {
				LOGGER.error("Error during the DaoConnection getting: ", e);
				throw new RuntimeException(e);
			}
		}
		return instance;
	}
}
