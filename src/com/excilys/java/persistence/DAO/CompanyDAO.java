package com.excilys.java.persistence.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.java.mapper.CompanyMapper;
import com.excilys.java.model.Company;

public class CompanyDAO extends DAO<Company>{

	private static final String SELECT_ALL = "SELECT id, name FROM company ORDER BY id";
	private static final String SELECT_WITH_ID = "SELECT id, name FROM company WHERE id = ?";
	private static CompanyDAO companyDAO;
	
	
	public CompanyDAO() {
	}

	/**
     * Create the instance of companyDAO if it not exists
     * @return companyDAO
     */
	
	public static CompanyDAO getInstance() {
		if (companyDAO == null) {
			companyDAO = new CompanyDAO();
        }
        return companyDAO;
    }

	@Override
	public ArrayList<Company> getAll() {
		ArrayList<Company> companies= new ArrayList();
		
		try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_ALL);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()){
            	Company company = CompanyMapper.map(result);
            	companies.add(company);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		System.out.println(companies);
		return companies;
		
	}
	
	@Override
	public Company findById(Long id) {
		Company company = new Company();
		if(id!=null) {
			
			try {
	            PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_WITH_ID);
	            preparedStatement.setLong(1, id);
	            ResultSet result = preparedStatement.executeQuery();
	            while (result.next()){
	            	company = CompanyMapper.map(result);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		}
		System.out.println(company);
		return company;
	}
	
	 

}
