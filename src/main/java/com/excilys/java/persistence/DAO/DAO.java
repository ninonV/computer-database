package com.excilys.java.persistence.DAO;

import java.util.List;

import com.excilys.java.model.Page;

public abstract class DAO<T> {
	
	/**
	 *Return the complete list 
	 * @return Arraylist
	 * @throws Exception 
	 */
	public abstract List<T> getAll();
	
	
	/**
	 * Return the object corresponding to the id 
	 * @param id
	 * @return T
	 * @throws Exception 
	 */
	public abstract T findById(Long id);
	
	
	/**
	 * Inform if the object is already in the BDD
	 * @param id
	 * @return boolean
	 * @throws Exception 
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
	 * @throws Exception 
	 */
	public abstract List<T> getPage(Page page);
	
}
