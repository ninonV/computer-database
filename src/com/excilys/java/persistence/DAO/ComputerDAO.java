package com.excilys.java.persistence.DAO;
import com.excilys.java.model.Computer;

import java.sql.Connection;
import java.util.ArrayList;


public class ComputerDAO extends DAO<Computer>{
	
	private static final String SELECT_ALL = "";
	private static final String SELECT_WITH_ID = "";
	private static final String CREATE = "";
	private static final String UPDATE = "";
	private static final String DELETE = "";
	
	private ComputerDAO computerDAO;
//	private Connexion connexion = MysqlConnect.getConnection();
	
	public ComputerDAO(Connection connect) {
		super(connect);
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public ArrayList<Computer> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Computer findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Add a computer
	 * @param computer
	 */
	
	public void create(Computer computer) {
		
	}
	
	/**
	 * Update an existing ciomputer
	 * @param computer
	 */
	
	public void update(Computer computer) {
		
	}
	
	/**
	 * Delete an existing computer
	 * @param computer
	 */
	
	public void delete(Computer computer) {
		
	}

}
