package com.excilys.java.persistence.DAO.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.excilys.java.model.Company;

/**
 * Mapping a ResultSet to a Company
 * @author ninonV
 */

public class CompanyMapper implements RowMapper<Company> {
	
	private static Logger logger = LoggerFactory.getLogger(CompanyMapper.class);
	
	/**
	 * Convert a ResultSet to a Company
	 * @param result
	 * @return company
	 */

	@Override
	public Company mapRow(ResultSet rs, int rowNum) {
		Company company = new Company();
		try {
			company = new Company.Builder()
					.setId(rs.getLong("id"))
					.setName(rs.getString("name"))
					.build();
		} catch (SQLException e) {
			logger.error("Error when mapping a ResultSet to a Company",e);
		}	
		return company;
	}
	
}
