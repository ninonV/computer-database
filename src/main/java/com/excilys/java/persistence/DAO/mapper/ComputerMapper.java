package com.excilys.java.persistence.DAO.mapper;

import java.sql.ResultSet;
import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.java.model.Company;
import com.excilys.java.model.Computer;

/**
 * Mapping an object to a computer
 * @author ninonV
 *
 */

public class ComputerMapper {
	
	private static Logger logger = LoggerFactory.getLogger(ComputerMapper.class);
	
	/**
	 * Convert a ResultSet to a Computer
	 * @param result
	 * @return computer
	 */
	
	public static Computer mapResultSet(ResultSet result){
		Computer computer = new Computer();
		try {
			Long id = result.getLong("id");
	    	String name = result.getString("name");
	    	LocalDate introduced = null;
	    	if (result.getDate("introduced")!=null) {
	    		introduced = result.getDate("introduced").toLocalDate();
	    	}
	    	LocalDate discontinued = null; 
	    	if (result.getDate("discontinued")!=null) {
	    		discontinued = result.getDate("discontinued").toLocalDate();
	    	}
	    	
	    	Long company_id = result.getLong("company_id");
	    	String company_name = result.getString("company_name");
	    	
	    	computer.setId(id);
	    	computer.setName(name);
			computer.setIntroduced(introduced);
			computer.setDiscontinued(discontinued);
	    	computer.setCompany(new Company.Builder().setId(company_id).setName(company_name).build());
	    	
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error when mapping a ResultSet to a Computer",e);
		}
		return computer;
	}
	
}
