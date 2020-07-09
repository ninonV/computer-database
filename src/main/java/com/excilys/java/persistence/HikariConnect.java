package com.excilys.java.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariDataSource;

@Component
public class HikariConnect {
	
	private static Connection connection;
	private static Logger logger = LoggerFactory.getLogger(HikariConnect.class);
	
	@Autowired
	private HikariDataSource dataSource;
	
	 public Connection getConnection() throws SQLException{
		 if (connection == null || connection.isClosed() ) {
				try {   
					return dataSource.getConnection();
				}catch ( SQLException e) {
					logger.error("Error during the connexion with Hikari",e);
				}
		 }
		return connection;
	 }

}
