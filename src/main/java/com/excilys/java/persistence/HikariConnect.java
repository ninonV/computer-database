package com.excilys.java.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariConnect {
	
	private static Connection connection;
	private static Logger logger = LoggerFactory.getLogger(HikariConnect.class);
	private static HikariConfig config = new HikariConfig("/datasource.properties");
	private static HikariDataSource ds = new HikariDataSource(config);
	
	 public static Connection getConnexion() throws SQLException{
		 if (connection == null || connection.isClosed() ) {
				try {   
					return ds.getConnection();
				}catch ( SQLException e) {
					logger.error("Error during the connexion with Hikari",e);
				}
		 }
		return connection;
	 }

}
