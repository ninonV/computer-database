package com.excilys.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.java.model.Company;
import com.excilys.java.model.Page;
import com.excilys.java.persistence.DAO.CompanyDAO;

@Service
public class CompanyService {
	
	/**
	 *  Class doing the relation with the CompanyDAO  
	 *  @author ninonV
	 *  **/
	
	@Autowired
	private CompanyDAO companyDAO;
	
	public List<Company> listCompanies(){
		return companyDAO.getAll();
	}
	
	public Company findbyID(Long id) {
		return companyDAO.findById(id);
	}
	
	public void deleteCompany(Long id) {
		companyDAO.delete(id);
	}

	public boolean existCompany(Long id) {
		return companyDAO.exist(id);
	}
	
	public int countCompany() {
		return companyDAO.count();
	}
	
	public List<Company> getListPage(Page page){
		return companyDAO.getPage(page);
	}
	
}
