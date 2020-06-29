package com.excilys.java.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.java.DTO.CompanyDTO;
import com.excilys.java.model.Company;

/**
 * Mapping a ResultSet to a Company
 * @author ninonV
 */

public class CompanyMapper {
	
	private static Logger logger = LoggerFactory.getLogger(CompanyMapper.class);
	
	/**
	 * Convert a ResultSet to a Company
	 * @param result
	 * @return company
	 */
	
	public static Company mapResultSet(ResultSet result){
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
	
	/**
	 * Convert a CompanyDTO to a Company
	 * @param companyDTO
	 * @return company
	 */
	public static Company mapDtoToCompany(CompanyDTO companyDTO){
		Company company = new Company(); 
		Long id = Long.parseLong(companyDTO.getIdCompany());
		company.setIdCompany(id);
		company.setName(companyDTO.getName());
		return company;
	}
	
	/**
	 * Convert a Company to a CompanyDTO
	 * @param company
	 * @return companyDTO
	 */
	public static CompanyDTO mapCompanyToDTO(Company company){
		CompanyDTO companyDTO = new CompanyDTO(); 
		companyDTO.setIdCompany(company.getIdCompany().toString());
		companyDTO.setName(company.getName());
		return companyDTO;
	}
	
}
