package com.excilys.java.service;

import java.util.ArrayList;
import com.excilys.java.model.Company;
import com.excilys.java.persistence.DAO.CompanyDAO;

public class CompanyService {
	
	private static CompanyService companyService;
	private CompanyDAO companyDAO;
	
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

}
