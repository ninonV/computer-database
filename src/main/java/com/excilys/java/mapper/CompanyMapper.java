package com.excilys.java.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.java.model.Company;

/**
 * Mapping a ResultSet to a Company
 * @author ninonV
 */

public class CompanyMapper {
	
	private static Logger logger = LoggerFactory.getLogger(CompanyMapper.class);
	
	public static Company map(ResultSet result){
		Company company = new Company(); 
		try {
			Long id = result.getLong("id");
			String name = result.getString("name");
			company.setIdCompany(id);
			company.setName(name);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("Error when mapping a ResultSet to a Company",e);
		}
		return company;
	}
}