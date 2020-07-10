package com.excilys.java.persistence.DAO.mapper;

import java.sql.ResultSet;
import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.excilys.java.model.Company;
import com.excilys.java.model.Computer;

/**
 * Mapping an object to a computer
 * @author ninonV
 *
 */

public class ComputerMapper implements RowMapper<Computer> {
	
	private static Logger logger = LoggerFactory.getLogger(ComputerMapper.class);
	
	/**
	 * Convert a ResultSet to a Computer
	 * @param result
	 * @return computer
	 */

	@Override
	public Computer mapRow(ResultSet rs, int rowNum) {
		Computer computer = new Computer();
		try {
			computer.setId(rs.getLong("id"));
			computer.setName(rs.getString("name"));
			
	    	LocalDate introduced = null;
	    	if (rs.getDate("introduced")!=null) {
	    		introduced = rs.getDate("introduced").toLocalDate();
	    	}
			computer.setIntroduced(introduced);
			
	    	LocalDate discontinued = null; 
	    	if (rs.getDate("discontinued")!=null) {
	    		discontinued = rs.getDate("discontinued").toLocalDate();
	    	}
			computer.setDiscontinued(discontinued);

	    	computer.setCompany(new Company.Builder()
	    			.setId(rs.getLong("company_id"))
	    			.setName(rs.getString("company_name"))
	    			.build());
			
		}catch (Exception e) {
			logger.error("Error when mapping a ResultSet to a Computer",e);
		}
		return computer;
	}
	
}
