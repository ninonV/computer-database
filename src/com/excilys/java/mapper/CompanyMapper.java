package com.excilys.java.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.excilys.java.model.Company;

public class CompanyMapper {

	
	public static Company map(ResultSet result){
		Company company = new Company(); 
		try {
			Long id = result.getLong("id");
			String name = result.getString("name");
			company.setIdCompany(id);
			company.setName(name);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return company;
	}
}
