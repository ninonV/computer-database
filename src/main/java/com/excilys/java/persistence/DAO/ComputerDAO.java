package com.excilys.java.persistence.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.excilys.java.model.Computer;
import com.excilys.java.model.Page;
import com.excilys.java.persistence.HikariConnect;
import com.excilys.java.persistence.DAO.mapper.ComputerMapper;

/**
 *  Class doing the relation with the table computer  
 *  @author ninonV
 *  **/

@Repository
public class ComputerDAO extends DAO<Computer>{
	
	private static final String GET_ALL = "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.name AS company_name "
			+ "FROM computer LEFT JOIN company ON company_id = company.id ORDER BY computer.id ";
	private static final String GET_WITH_ID = "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.name AS company_name "
			+ "FROM computer LEFT JOIN company ON company_id = company.id WHERE computer.id = ? ";
	private static final String CREATE = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,?)";
	private static final String UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id= ?";
	private static final String DELETE = "DELETE FROM computer WHERE id = ? ";
	private static final String DELETE_COMPUTER_FROM_COMPANY = "DELETE FROM computer WHERE company_id = ?";
	private static final String COUNT = "SELECT COUNT(id) FROM computer";
	private static final String COUNT_SEARCH = "SELECT COUNT(computer.id) FROM computer LEFT JOIN company ON company_id = company.id "
			+ "WHERE computer.name LIKE ? OR company.name LIKE ?";
	private static final String GET_PAGE = "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.name AS "
			+ "company_name FROM computer LEFT JOIN company ON company_id = company.id ORDER BY %s LIMIT ? OFFSET ?";
	private static final String GET_PAGE_SEARCH = "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.name AS company_name "
			+ "FROM computer LEFT JOIN company ON company_id = company.id WHERE computer.name LIKE ? OR company.name LIKE ? ORDER BY %s LIMIT ? OFFSET ?";

	
	private static Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
	
	@Autowired
	private HikariConnect dataSource;
	@Autowired
	private JdbcTemplate jdbcTemplate; 
	
	@Override
	public List<Computer> getAll() {
		List<Computer> computers = new ArrayList<Computer>();
		try ( Connection connect = dataSource.getConnection()){
			computers=jdbcTemplate.query(GET_ALL, new ComputerMapper());
        } catch (SQLException e) {
            logger.error("Error when listing all computers",e);
        }
		return computers;	
	}
	
	@Override
	public Computer findById(Long id) {
		Computer computer = new Computer();
		if(id!=null) {
			try ( Connection connect = dataSource.getConnection()){
				computer = jdbcTemplate.queryForObject(GET_WITH_ID, new ComputerMapper(), id);
	        } catch (SQLException e) {
	           logger.error("Error when finding a computer with its ID",e);
	        }
		}
		return computer;
	}
	
	/**
	 * Add a computer
	 * @param computer
	 */
	
	public void create(Computer computer) {
		try (Connection connect = dataSource.getConnection()) {
            Date  dateSQLIntroduced = null;
            Date  dateSQLDiscontinued = null;
            if (computer.getIntroduced()!=null) {
            	dateSQLIntroduced=Date.valueOf(computer.getIntroduced());
            }
            if (computer.getDiscontinued()!=null) {
            	dateSQLDiscontinued=Date.valueOf(computer.getDiscontinued());
            }
           
            jdbcTemplate.update(CREATE, 
            		computer.getName(), 
            		dateSQLIntroduced,
            		dateSQLDiscontinued, 
            		computer.getCompany().getId()==0 ? null : computer.getCompany().getId());         
            } catch (SQLException e) {
            logger.error("Error when creating a computer",e);
        }
	}
	
	/**
	 * Update an existing computer
	 * @param computer
	 */
	public void update(Computer computer) {
		try (Connection connect = dataSource.getConnection()) {
            Date  dateSQLIntroduced = null;
            Date  dateSQLDiscontinued = null;
            if (computer.getIntroduced()!=null) {
            	dateSQLIntroduced=Date.valueOf(computer.getIntroduced());
            }
            if (computer.getDiscontinued()!=null) {
            	dateSQLDiscontinued=Date.valueOf(computer.getDiscontinued());
            }
            /*if (computer.getCompany().getId()==null || computer.getCompany().getId()==0) {
            	preparedStatement.setNull(4, Types.BIGINT);
            }else {
            	 preparedStatement.setLong(4, computer.getCompany().getId());
            }*/
            jdbcTemplate.update(UPDATE, 
            		computer.getName(), 
            		dateSQLIntroduced,
            		dateSQLDiscontinued, 
            		computer.getCompany().getId()==0 ? null : computer.getCompany().getId(),
            		computer.getId());
        } catch (SQLException e) {
            logger.error("Error when updating a computer",e);
        }
	}
	
	@Override
	public void delete(Long id) {
		try (Connection connect = dataSource.getConnection()) {
			jdbcTemplate.update(DELETE, id);
        } catch (SQLException e) {
            logger.error("Error when deleting a computer",e);
        }		
	}
	
	/**
	 * Check if the computer exists in the BDD
	 * @param idComputer
	 * @return boolean 
	 */
	@Override
	public boolean exist(Long id){
		boolean isInBDD = false; 
		if ((this.findById(id)).getId()!=null) {
			isInBDD=true; 
		}
		return isInBDD; 
	}

	/**
	 * Count the number of computers : total or depending of the research
	 * @param search string
	 * @return int total
	 */
	public int count(String search) {
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

	/**
	 * List of the computers for a page depending of the research and the order asked
	 * @param page, search string, order string
	 * @return computers
	 */
	public List<Computer> getPage(Page page, String search, String order) {
		List<Computer> computers= new ArrayList<Computer>();
		try (Connection connect = dataSource.getConnection()){
			if(order==null || order.isEmpty() || 
					(!order.equals("computer.id") &  !order.equals("computer.name ASC") &   !order.equals("computer.name DESC") & !order.equals("introduced ASC") & !order.equals("introduced DESC")
					& !order.equals("discontinued ASC") & !order.equals("discontinued DESC") & !order.equals("company.name ASC") & !order.equals("company.name DESC"))) {
				order="computer.id";
			}
			if(search==null || search.isEmpty()) {
				String orderChoice= String.format(GET_PAGE,order);
				computers = jdbcTemplate.query(orderChoice, 
						new ComputerMapper(), 
						page.getLinesPage() , 
						page.getFirstLine()-1);
			}else {
				String orderChoice= String.format(GET_PAGE_SEARCH,order);
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
	
	/**
	 * Delete the computers associated with the company deleted
	 * @param idCompany
	 */
	public void deleteComputersFromCompany(Long id) {
		try (Connection connect = dataSource.getConnection()) {
	            jdbcTemplate.update(DELETE_COMPUTER_FROM_COMPANY, id);
	        } catch (SQLException e) {
	            logger.error("Error when deleting a computer for the company desired",e);
	        }	
	}
}
