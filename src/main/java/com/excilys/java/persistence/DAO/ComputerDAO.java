package com.excilys.java.persistence.DAO;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.java.model.Computer;

/**
 *  Class doing the relation with the table computer  
 *  @author ninonV
 *  **/

@Repository
public interface ComputerDAO extends JpaRepository<Computer, Long> {
	
	List<Computer> findByNameContaining(Pageable pageable, String search, String order);

	int countByNameContaining(String search);
	
	/*
	 * 	public int count(String search) {
		int total = 0;
		try (Connection connect = dataSource.getConnection()){
			if(search==null) {
				total = jdbcTemplate.queryForObject(COUNT, Integer.class);
			}else {
				total = jdbcTemplate.queryForObject(COUNT_SEARCH, Integer.class , "%"+search+"%" , "%"+search+"%");
			}
		 } catch (SQLException e) {
	            logger.error("Error when counting the number of computers",e);
	        }
            return total; 
	}
	 * 
	 * 
	 * public List<Computer> getPage(Page page, String search, String order) {
		List<Computer> computers= new ArrayList<Computer>();
		try (Connection connect = dataSource.getConnection()){
			if(order==null || order.isEmpty() || 
					(!order.equals("computer.id") &  !order.equals("computer.name ASC") &   !order.equals("computer.name DESC") & !order.equals("introduced ASC") & !order.equals("introduced DESC")
					& !order.equals("discontinued ASC") & !order.equals("discontinued DESC") & !order.equals("company.name ASC") & !order.equals("company.name DESC"))) {
				order="computer.id";
			}
			if(search==null || search.isEmpty()) {
				String orderChoice= String.format(GET_PAGE,(order.split(" "))[0],order);
				computers = jdbcTemplate.query(orderChoice, 
						new ComputerMapper(), 
						page.getLinesPage() , 
						page.getFirstLine()-1);
			}else {
				String orderChoice= String.format(GET_PAGE_SEARCH,(order.split(" "))[0],order);
				computers = jdbcTemplate.query(orderChoice, 
						new ComputerMapper(),
						"%"+search+"%",
						"%"+search+"%",
						page.getLinesPage() , 
						page.getFirstLine()-1);
			}
           
        } catch (SQLException e) {
            logger.error("Error when listing the computers on a page",e);
        }
		return computers;
	}
	 */
}