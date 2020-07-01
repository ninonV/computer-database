package com.excilys.java.DTO.mapper;

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
	 * Convert a ComputerDTO to a Computer
	 * @param computerDTO
	 * @return computer
	 */
	public static Computer mapDtoToComputer(ComputerDTO computerDTO){
		Computer computer = new Computer();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {
			Long id = null;
			if(computerDTO.getId()!=null) {
				id=Long.parseLong(computerDTO.getId());
			}
			String name = computerDTO.getName();
	    	LocalDate introduced = null;
	    	if (!computerDTO.getIntroduced().equals(null) && !computerDTO.getIntroduced().isEmpty()) {
	    		introduced = LocalDate.parse(computerDTO.getIntroduced(), formatter);
	    	}
	    	LocalDate discontinued = null; 
	    	if (!computerDTO.getDiscontinued().equals(null) && !computerDTO.getDiscontinued().isEmpty()) {
	    		discontinued = LocalDate.parse(computerDTO.getDiscontinued(), formatter);
	    	}
	    	
	    	Long company_id = Long.parseLong(computerDTO.getCompany().getId());
	    	String company_name = computerDTO.getCompany().getName();
	    	
	    	computer.setId(id);
	    	computer.setName(name);
			computer.setIntroduced(introduced);
			computer.setDiscontinued(discontinued);
	    	computer.setCompany(new Company.Builder().setId(company_id).setName(company_name).build());
	    	
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
		if(computer.getId()!=null) {
			computerDTO.setId(computer.getId().toString());
		}
		if(computer.getName()!=null) {
			computerDTO.setName(computer.getName());
		}
		if(computer.getIntroduced()!=null) {
			computerDTO.setIntroduced(computer.getIntroduced().toString());
		}
		if(computer.getDiscontinued()!=null) {
			computerDTO.setDiscontinued(computer.getDiscontinued().toString());
		}
		if(computer.getCompany()!=null && computer.getCompany().getId()!=null){ 
			CompanyDTO companyDTO = new CompanyDTO.Builder()
					.setId(computer.getCompany().getId().toString())
					.setName(computer.getCompany().getName())
					.build();
			computerDTO.setCompany(companyDTO);
		}
		return computerDTO;
	}

}
