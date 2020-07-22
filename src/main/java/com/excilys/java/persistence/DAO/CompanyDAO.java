package com.excilys.java.persistence.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.java.model.Company;
/**
 *  Class doing the relation with the table company  
 *  @author ninonV
 *  **/
@Repository
public interface CompanyDAO extends JpaRepository<Company, Long> {
	
	/*
	public List<Company> getPage(Page page) {
		List<Company> companies= new ArrayList<Company>();
		try (Connection connect = dataSource.getConnection()){
            companies = jdbcTemplate.query(GET_PAGE, new CompanyMapper(), page.getLinesPage(), page.getFirstLine()-1);
        } catch (SQLException e) {
            logger.error("Error when listing the companies on a page",e);
        }
		return companies;
	}
	 */
}
