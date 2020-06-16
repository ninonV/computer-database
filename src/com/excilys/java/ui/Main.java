package com.excilys.java.ui;

import java.sql.Date;
import com.excilys.java.model.Company;
import com.excilys.java.model.Computer;
import com.excilys.java.persistence.DAO.CompanyDAO;
import com.excilys.java.persistence.DAO.ComputerDAO;

public class Main {

	public static void main(String[] args) {
		CompanyDAO companyDAOtest=new CompanyDAO();
		// companyDAOtest.getAll();
		// companyDAOtest.findById(24l);
		
		ComputerDAO computerDAOtest=new ComputerDAO();
		//computerDAOtest.getAll();
		//computerDAOtest.findById(15l);
		
		//Date j = new Date(System.currentTimeMillis());
		//Company company = companyDAOtest.findById(24l);
		//Computer comp= new Computer(600l, "TEST", j, j, company);
		//computerDAOtest.create(comp);
		//Computer comp= new Computer(600l, "NOMDIFF", j, j, company);
		//computerDAOtest.update(comp);
		computerDAOtest.delete(600l);
		computerDAOtest.getAll();
	}
}
