package com.excilys.java.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.java.model.Company;
import com.excilys.java.model.Computer;

/**
 * Mapping a ResultSet to a Company
 * @author ninonV
 *
 */

public class ComputerMapper {
	
	private static Logger logger = LoggerFactory.getLogger(ComputerMapper.class);
	
	public static Computer map(ResultSet result){
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
	    	
	    	computer.setIdComputer(id);
	    	computer.setName(name);
			computer.setIntroduced(introduced);
			computer.setDiscontinued(discontinued);
	    	computer.setManufacturer(new Company(company_id, company_name));
	    	
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error when mapping a ResultSet to a Computer",e);
		}
		return computer;
	}

}
