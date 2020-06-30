package com.excilys.java.DTO.mapper;

import com.excilys.java.DTO.CompanyDTO;
import com.excilys.java.model.Company;

/**
 * Mapping a ResultSet to a Company
 * @author ninonV
 */

public class CompanyMapper {
	
	/**
	 * Convert a CompanyDTO to a Company
	 * @param companyDTO
	 * @return company
	 */
	public static Company mapDtoToCompany(CompanyDTO companyDTO){
		Company company = new Company.Builder()
									.setId(Long.parseLong(companyDTO.getId()))
									.setName(companyDTO.getName())
									.build();
		return company;
	}
	
	/**
	 * Convert a Company to a CompanyDTO
	 * @param company
	 * @return companyDTO
	 */
	public static CompanyDTO mapCompanyToDTO(Company company){
		CompanyDTO companyDTO = new CompanyDTO.Builder()
											.setId(company.getId().toString())
											.setName(company.getName())
											.build();
		return companyDTO;
	}
	
}
