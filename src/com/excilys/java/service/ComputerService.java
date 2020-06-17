package com.excilys.java.service;

import java.util.ArrayList;
import com.excilys.java.model.Computer;
import com.excilys.java.persistence.DAO.CompanyDAO;
import com.excilys.java.persistence.DAO.ComputerDAO;

public class ComputerService {
	
	private static ComputerService computerService;
	private static ComputerDAO computerDAO = ComputerDAO.getInstance();
	private static CompanyService companyService = CompanyService.getInstance();
	
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
	
	public boolean existComputer(Long id) {
		return computerDAO.exist(id);
	}
	
	public boolean allowCreation (Computer computer) {
		boolean creationAuthorized = true; 
		if (computer.getName().length()==0) {
			System.out.println("The name is mandatory");
			creationAuthorized = false; 
		}else if (computer.getIntroduced()!=null && computer.getDiscontinued()!=null && computer.getDiscontinued().before(computer.getIntroduced())) {
				System.out.println("The date of introduction should be before the date of discontinuation");
				creationAuthorized = false; 
			} if (computer.getManufacturer()!=null) {
				if (!companyService.existCompany(computer.getManufacturer().getIdCompany())) {
					System.out.println("The company should exist");
					creationAuthorized = false; 
				}
			} 
		return creationAuthorized;
	}
}
