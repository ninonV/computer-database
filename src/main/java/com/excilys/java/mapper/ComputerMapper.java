package com.excilys.java.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.java.DTO.CompanyDTO;
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
	public static Computer mapDtoToComputer(ComputerDTO computerDTO){
		Computer computer = new Computer();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {
			String name = computerDTO.getName();
	    	LocalDate introduced = null;
	    	if (!computerDTO.getIntroduced().equals(null) && !computerDTO.getIntroduced().isEmpty()) {
	    		introduced = LocalDate.parse(computerDTO.getIntroduced(), formatter);
	    	}
	    	LocalDate discontinued = null; 
	    	if (!computerDTO.getDiscontinued().equals(null) && !computerDTO.getDiscontinued().isEmpty()) {
	    		discontinued = LocalDate.parse(computerDTO.getDiscontinued(), formatter);
	    	}
	    	
	    	Long company_id = Long.parseLong(computerDTO.getManufacturer().getIdCompany());
	    	String company_name = computerDTO.getManufacturer().getName();
	    	
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
	

	/**
	 * Convert a Computer to a ComputerDTO
	 * @param computer
	 * @return computerDTO
	 */
	public static ComputerDTO mapComputertoDTO(Computer computer){
		ComputerDTO computerDTO = new ComputerDTO();
		CompanyDTO companyDTO = new CompanyDTO();
		computerDTO.setIdComputer(computer.getIdComputer().toString());
		computerDTO.setName(computer.getName());
		computerDTO.setIntroduced(computer.getIntroduced().toString());
		computerDTO.setDiscontinued(computer.getDiscontinued().toString());
		companyDTO.setIdCompany(computer.getManufacturer().getIdCompany().toString());
		companyDTO.setName(computer.getManufacturer().getName());
		computerDTO.setManufacturer(companyDTO);
		return computerDTO;
	}

}
