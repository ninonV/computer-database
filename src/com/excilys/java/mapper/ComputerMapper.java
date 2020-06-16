package com.excilys.java.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.java.model.Company;
import com.excilys.java.model.Computer;


public class ComputerMapper {
	
	public static Computer map(ResultSet result){
		Computer computer = new Computer();
		try {
			Long id = result.getLong("id");
	    	String name = result.getString("name");
	    	Date introduced = result.getDate("introduced");
	    	Date discontinued = result.getDate("discontinued");
	    	Long company_id = result.getLong("company_id");
	    	String company_name = result.getString("company_name");
	    	computer.setIdComputer(id);
	    	computer.setName(name);
	    	computer.setIntroduced(introduced);
	    	computer.setDiscontinued(discontinued);
	    	computer.setManufacturer(new Company(company_id, company_name));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return computer;
	}

}
