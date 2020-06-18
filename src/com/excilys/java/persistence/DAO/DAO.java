package com.excilys.java.persistence.DAO;

import java.sql.Connection;
import java.util.List;

import com.excilys.java.model.Page;
import com.excilys.java.persistence.MysqlConnect;

public abstract class DAO<T> {
	
	/**
	 *Return the complete list 
	 * @return Arraylist
	 */
	public abstract List<T> getAll();
	
	
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
	
	/**
	 * Count all the objects in the BDD
	 * @return boolean
	 */
	public abstract int count();
	
	/**
	 * Return the list of object per page
	 * @return ArrayList
	 */
	public abstract List<T> getPage(Page page);
	
}
