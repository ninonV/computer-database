package com.excilys.java.service;

import java.util.ArrayList;
import com.excilys.java.model.Company;
import com.excilys.java.model.Page;
import com.excilys.java.persistence.DAO.CompanyDAO;

public class CompanyService {
	
	/**
	 *  Class doing the relation with the CompanyDAO  
	 *  @author ninonV
	 *  **/
	
	private static CompanyService companyService;
	private static CompanyDAO companyDAO = CompanyDAO.getInstance();
	
	public CompanyService() {
	}
	
	/**
     * Create the instance of companyService if it not exists
     * @return companyService
     */

	public static CompanyService  getInstance() {
		if (companyService == null) {
			companyService = new CompanyService();
        }
        return companyService;
    }
	
	public ArrayList<Company> listCompanies(){
		return companyDAO.getAll();
	}
	
	public Company findbyID(long id) {
		return companyDAO.findById(id);
	}

	public boolean existCompany(Long id) {
		return companyDAO.exist(id);
	}
	
	public int countCompany() {
		return companyDAO.count();
	}
	
	public ArrayList<Company> getListPage(Page page){
		return companyDAO.getPage(page);
	}
	
}
