package com.excilys.java.service;

import java.util.List;

import com.excilys.java.model.Computer;
import com.excilys.java.model.Page;
import com.excilys.java.persistence.DAO.ComputerDAO;

/**
 *  Class doing the relation with the ComputerDAO  
 *  @author ninonV
 *  **/

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
	
	public List<Computer> listComputers() {
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
	
	public int countComputer() {
		return computerDAO.count();
	}
	
	public List<Computer> getListPage(Page page) {
		return computerDAO.getPage(page);
	}
	
	
	/**
	 * Check the conditions to create or update a computer
	 * @param computer
	 * @return boolean
	 */
	
	public boolean allowCreation (Computer computer) {
		boolean creationAuthorized = true; 
		if (computer.getName().length()==0) {
			System.err.println("The name is mandatory");
			creationAuthorized = false; 
		}else if (computer.getIntroduced()!=null && computer.getDiscontinued()!=null && computer.getDiscontinued().isBefore(computer.getIntroduced())) {
				System.err.println("Introduced date must be before discontinued date");
				creationAuthorized = false; 
			} if (computer.getManufacturer().getIdCompany()!=0) {
				if (!companyService.existCompany(computer.getManufacturer().getIdCompany())) {
					System.err.println("The company should exist");
					creationAuthorized = false; 
				}
			} 
		return creationAuthorized;
	}
}
