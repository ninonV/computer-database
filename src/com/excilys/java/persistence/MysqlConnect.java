package com.excilys.java.persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *  Class doing the connexion to mySQL 
 *  @author ninonV
 *  **/

public class MysqlConnect {
	
	private static Connection connection;
	private static final String resourcesFile="src/resources/db.properties";
	
	/**
     * Create the instance of connexion if it not exists
     * @return instance of connexion
	 * @throws SQLException 
     */
	
	public static Connection getInstance() throws SQLException {
		if (connection == null || connection.isClosed() ) {
			try {
				Properties properties = new Properties();
				InputStream input = new FileInputStream(resourcesFile);
				
				properties.load(input);
	
	            String url = properties.getProperty("jdbc.url");
	            String driver = properties.getProperty("jdbc.driver");
	            String user = properties.getProperty("jdbc.user");
	            String password = properties.getProperty("jdbc.password");
	            
	            input.close();
	            Class.forName(driver);
			    connection = DriverManager.getConnection( url, user, password );	

			} catch ( SQLException | ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}	
		}
		return connection;
	}
	
}