package com.excilys.java.persistence.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.excilys.java.model.Company;

public class CompanyDAO extends DAO<Company>{

	private static final String SELECT_ALL = "SELECT id, name FROM company ORDER BY id";
	private static final String SELECT_WITH_ID = "SELECT id, name FROM company WHERE id = ?";
	private CompanyDAO companyDAO;
//	private Connexion connexion = MysqlConnect.getConnection();
	
	public CompanyDAO(Connection connect) {
		super(connect);
	}	

	@Override
	public ArrayList<Company> getAll() {
		ArrayList<Company> companies= new ArrayList();
		
		try {
            PreparedStatement preparedStatement = connexion.prepareStatement(SELECT_ALL);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()){
            	Company company = new Company();
            	Long id = result.getLong("id");
            	String name = result.getString("name");
            	company.setIdCompany(id);
            	company.setName(name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		

		return companies;
	}
	
	@Override
	public Company findById(Long id) {
		Company company = new Company();
		if(id!=null) {
			
			try {
	            PreparedStatement preparedStatement = this.connect.prepareStatement(SELECT_WITH_ID);
	            preparedStatement.setLong(1, id);
	            ResultSet result = preparedStatement.executeQuery();
	            while (result.next()){
	            	String name = result.getString("name");
	            	company.setIdCompany(id);
	            	company.setName(name);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		}
		return company;
	}
	
	 

}
