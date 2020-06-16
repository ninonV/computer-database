package com.excilys.java.service;

import java.util.ArrayList;
import com.excilys.java.model.Computer;
import com.excilys.java.persistence.DAO.ComputerDAO;

public class ComputerService {
	
	private static ComputerService computerService;
	private ComputerDAO computerDAO;
	
	public ComputerService() {
	}
	
	/**
     * Create the instance of companyService if it not exists
     * @return companyService
     */

	public static ComputerService  getInstance() {
		if (computerService== null) {
			computerService = new ComputerService();
        }
        return computerService;
    }
	
	public ArrayList<Computer> listComputers(){
		return computerDAO.getAll();
	}
	
	public Computer findbyID(long id) {
		return computerDAO.findById(id);
	}
	
	public void createComputer(Computer computer) {
		computerDAO.create(computer);
	}
	
	public void updateComputer(Computer computer) {
		computerDAO.update(computer);
	}
	
	public void deleteComputer(Long id) {
		computerDAO.delete(id);
	}

}
