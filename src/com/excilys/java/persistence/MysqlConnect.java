package com.excilys.java.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *  Class doing the connexion to mySQL 
 *  @author ninonV
 *  **/

public class MysqlConnect {

	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/computer-database-db?serverTimezone=UTC"; 
	private static final String user = "admincdb";
	private static final String password = "qwerty1234";
	private static Connection connection;
	
	/**
     * Create the instance of connexion if it not exists
     * @return instance of connexion
     */
	
	public static Connection getInstance() {
		if (connection == null) {
			try {
				Class.forName(driver);
			    System.out.println("Driver O.K.");
			    
			    connection = DriverManager.getConnection( url, user, password );
				System.out.println("Connexion BDD YES!"); 		

			} catch ( SQLException | ClassNotFoundException e) {
				e.printStackTrace();
				}	
		}
		return connection;
	}
}