package com.excilys.java.persistence.DAO;

import java.sql.Connection;
import java.util.ArrayList;
import com.excilys.java.persistence.MysqlConnect;

public abstract class DAO<T> {
	
	public Connection connection = MysqlConnect.getInstance();
	
	/**
	 *Return the complete list 
	 * @return Arraylist
	 */
	public abstract ArrayList<T> getAll();
	
	
	/**
	 * Return the object corresponding to the id 
	 * @param id
	 * @return T
	 */
	public abstract T findById(Long id);
	
	
	/**
	 * Inform if the object is already in the BDD
	 * @param id
	 * @return boolean
	 */
	public abstract boolean exist(Long id);
	
}
