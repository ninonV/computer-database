package com.excilys.java.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.java.DTO.ComputerDTO;
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
	
	/**
	 * Convert a ComputerDTO to a Computer
	 * @param computerDTO
	 * @return computer
	 */
	public static Computer mapDTO(ComputerDTO computerDTO){
		Computer computer = new Computer();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-DD");
		try {
			Long id = Long.parseLong(computerDTO.getIdComputer());
			String name = computerDTO.getName();
	    	LocalDate introduced = null;
	    	if (computerDTO.getIntroduced()!=null || !computerDTO.getIntroduced().isEmpty()) {
	    		introduced = LocalDate.parse(computerDTO.getIntroduced(), formatter);
	    	}
	    	LocalDate discontinued = null; 
	    	if (computerDTO.getDiscontinued()!=null || !computerDTO.getDiscontinued().isEmpty()) {
	    		discontinued = LocalDate.parse(computerDTO.getDiscontinued(), formatter);
	    	}
	    	
	    	Long company_id = Long.parseLong(computerDTO.getManufacturer().getIdCompany());
	    	String company_name = computerDTO.getManufacturer().getName();
	    	
	    	computer.setIdComputer(id);
	    	computer.setName(name);
			computer.setIntroduced(introduced);
			computer.setDiscontinued(discontinued);
	    	computer.setManufacturer(new Company(company_id, company_name));
	    	
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error when mapping a ComputerDTO to a Computer",e);
		}
		return computer;
	}

}
