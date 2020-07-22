package com.excilys.java.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.excilys.java.model.Computer;
import com.excilys.java.model.Pagination;
import com.excilys.java.persistence.DAO.ComputerDAO;

/**
 *  Class doing the relation with the ComputerDAO  
 *  @author ninonV
 *  **/

@Service
public class ComputerService {
	
	@Autowired
	private ComputerDAO computerDAO;
	@Autowired
	private CompanyService companyService;

	
	public List<Computer> listComputers() {
		return computerDAO.findAll();
	}
	
	public Optional<Computer> findbyID(Long id) {
		return computerDAO.findById(id);
	}
	
	public void createComputer(Computer computer) {
		computerDAO.save(computer);
	}
	
	public void updateComputer(Computer computer) {
		computerDAO.save(computer);
	}
	
	public void deleteComputer(Long id) {
		computerDAO.deleteById(id);
	}
	
	public boolean existComputer(Long id) {
		return computerDAO.existsById(id);
	}
	
	
	public int countComputer(String search) {
		return computerDAO.countByNameContaining(search);
	}
	
	public List<Computer> getListPage(Pagination page, String search, String order) {
		if(order==null || order.isEmpty() || 
				(!order.equals("computer.id ASC") &  !order.equals("computer.name ASC") &   !order.equals("computer.name DESC") & !order.equals("introduced ASC") & !order.equals("introduced DESC")
				& !order.equals("discontinued ASC") & !order.equals("discontinued DESC") & !order.equals("company.name ASC") & !order.equals("company.name DESC"))) {
			order="computer.id ASC";
		}
		
		String orderColumn = order.split(" ")[0];
		String direction = order.split(" ")[1];
		Pageable pageRequest;
		
		if(search==null || search.isEmpty()) {
			if (direction.equals("ASC")) {
				pageRequest = PageRequest.of(page.getCurrentPage(), page.getLinesPage(), Sort.by(Sort.Direction.ASC, order.split(" ")[0]));
			}else {
				pageRequest = PageRequest.of(page.getCurrentPage(), page.getLinesPage(), Sort.by(Sort.Direction.DESC, order.split(" ")[0]));
			}
			Page<Computer> computersPage = computerDAO.findAll(pageRequest);
			return computersPage.getContent() ;
		}else {
			if (direction.equals("ASC")) {
				pageRequest = PageRequest.of(page.getCurrentPage(), page.getLinesPage(), Sort.by(Sort.Direction.ASC, order.split(" ")[0]));
			}else {
				pageRequest = PageRequest.of(page.getCurrentPage(), page.getLinesPage(), Sort.by(Sort.Direction.DESC, order.split(" ")[0]));
			}
			List<Computer> computersPage = computerDAO.findByNameContaining(pageRequest, search, order);
			return computersPage;
		}
		
	}
	
	/**
	 * Check the conditions to create or update a computer in CLI
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
			} if (computer.getCompany().getId()!=0) {
				if (!companyService.existCompany(computer.getCompany().getId())) {
					System.err.println("The company should exist");
					creationAuthorized = false; 
				}
			} 
		return creationAuthorized;
	}
}
