package com.excilys.java.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  Class doing the connexion to mySQL 
 *  @author ninonV
 *  **/

public class MysqlConnect {
	
	private static Connection connection;
	private static Logger logger = LoggerFactory.getLogger(MysqlConnect.class);
	
	/**
     * Create the instance of connexion if it not exists
     * @return instance of connexion
	 * @throws SQLException 
     */
	
	public static Connection getInstance() throws SQLException {
		if (connection == null || connection.isClosed() ) {
			try {
				Properties properties = new Properties();
				InputStream input = MysqlConnect.class.getClassLoader().getResourceAsStream("db.properties");
				
				properties.load(input);
	
				String driver = properties.getProperty("jdbc.driver");
	            String url = properties.getProperty("jdbc.url");
	            String user = properties.getProperty("jdbc.user");
	            String password = properties.getProperty("jdbc.password");
	            
	            input.close();
	            Class.forName(driver);
			    connection = DriverManager.getConnection( url, user, password );	

			} catch ( SQLException | ClassNotFoundException | IOException e) {
				e.printStackTrace();
				logger.error("Error during the connexion with MySQL",e);
			}	
		}
		return connection;
	}
	
}