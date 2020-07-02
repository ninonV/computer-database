package com.excilys.java.persistence.DAO;

import java.util.List;

import com.excilys.java.model.Page;

public abstract class DAO<T> {
	
	/**
	 *Return the complete list 
	 * @return List
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
	 * Delete an object
	 * @param object
	 */
	public abstract void delete(Long id);
	
}
