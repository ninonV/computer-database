package com.excilys.java.persistence.DAO;

import java.sql.Connection;
import java.util.ArrayList;
import com.excilys.java.model.Company;

public abstract class DAO<T> {
	
	protected Connection connect = null;

	public DAO(Connection connect) {
		super();
		this.connect = connect;
	} 
	
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
	
}
