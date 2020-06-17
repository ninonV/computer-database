package com.excilys.java.persistence.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.excilys.java.mapper.CompanyMapper;
import com.excilys.java.model.Company;
import com.excilys.java.model.Page;

public class CompanyDAO extends DAO<Company>{

	private static final String GET_ALL = "SELECT * FROM company ORDER BY id";
	private static final String GET_WITH_ID = "SELECT * FROM company WHERE id = ?";
	private static final String COUNT = "SELECT COUNT(*) FROM company";
	private static final String GET_PAGE = "SELECT * FROM company LIMIT ? OFFSET ?";
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
            PreparedStatement preparedStatement = this.connection.prepareStatement(GET_ALL);
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
	            PreparedStatement preparedStatement = this.connection.prepareStatement(GET_WITH_ID);
	            preparedStatement.setLong(1, id);
	            ResultSet result = preparedStatement.executeQuery();
	            while (result.next()){
	            	company = CompanyMapper.map(result);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		}
		return company;
	}
	
	@Override
	public boolean exist(Long id){
		boolean isInBDD = false; 
		if (this.findById(id)!=null) {
			isInBDD=true; 
		}
		return isInBDD; 
	}

	@Override
	public int count() {
		int total = 0;
		try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(COUNT);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            total = result.getInt(1);
		 } catch (SQLException e) {
	            e.printStackTrace();
	        }
            return total; 
	}

	@Override
	public ArrayList<Company> getPage(Page page) {
		ArrayList<Company> companies= new ArrayList();
		try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(GET_PAGE);
            preparedStatement.setInt(1, page.getLinesPage());
            preparedStatement.setInt(2, page.getFirstLine()-1);
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
}
