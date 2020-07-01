package com.excilys.java.persistence.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.java.model.Company;
import com.excilys.java.model.Page;
import com.excilys.java.persistence.HikariConnect;
import com.excilys.java.persistence.DAO.mapper.CompanyMapper;

/**
 *  Class doing the relation with the table company  
 *  @author ninonV
 *  **/

public class CompanyDAO extends DAO<Company>{

	private static final String GET_ALL = "SELECT id, name FROM company ORDER BY id";
	private static final String GET_WITH_ID = "SELECT id, name  FROM company WHERE id = ?";
	private static final String COUNT = "SELECT COUNT(id) FROM company";
	private static final String GET_PAGE = "SELECT id, name  FROM company LIMIT ? OFFSET ?";
	
	private static CompanyDAO companyDAO;
	private static Logger logger = LoggerFactory.getLogger(CompanyDAO.class);
	
	public CompanyDAO() {
	}

	/**
     * Create the instance of companyDAO if it not exists
     * @return companyDAO
     */
	
	public static CompanyDAO getInstance() {
		if (companyDAO == null) {
			companyDAO = new CompanyDAO();
        }
        return companyDAO;
    }

	@Override
	public List<Company> getAll() {
		List<Company> companies= new ArrayList<Company>();
		
		try (Connection connect = HikariConnect.getInstance();
			PreparedStatement preparedStatement= connect.prepareStatement(GET_ALL);
			ResultSet result = preparedStatement.executeQuery()) {
            while (result.next()){
            	Company company = CompanyMapper.mapResultSet(result);
            	companies.add(company);
            }
            result.next();
        } catch (SQLException e) {
            logger.error("Error when listing all companies",e);
        }
		return companies;
		
	}
	
	@Override
	public Company findById(Long id) {
		Company company = new Company();
		if(id!=null) {
			
			try (Connection connect = HikariConnect.getInstance();
				PreparedStatement preparedStatement= connect.prepareStatement(GET_WITH_ID))	 {
	            preparedStatement.setLong(1, id);
	            ResultSet result = preparedStatement.executeQuery();
	            while (result.next()){
	            	company = CompanyMapper.mapResultSet(result);
	            }
	            result.close();
	        } catch (SQLException e) {
	            logger.error("Error when finding a company with its ID",e);
	        }
		}
		return company;
	}
	
	@Override
	public boolean exist(Long id){
		boolean isInBDD = false; 
		if ((this.findById(id)).getId()!=null) {
			isInBDD=true; 
		}
		return isInBDD; 
	}

	@Override
	public int count() {
		int total = 0;
		try (Connection connect = HikariConnect.getInstance();
			PreparedStatement preparedStatement= connect.prepareStatement(COUNT);
			ResultSet result = preparedStatement.executeQuery()){
            result.next();
            total = result.getInt(1);
		 } catch (SQLException e) {
	            logger.error("Error when counting the number of companies",e);
	        }
            return total; 
	}

	@Override
	public List<Company> getPage(Page page) {
		List<Company> companies= new ArrayList<Company>();
		try (Connection connect = HikariConnect.getInstance();
			PreparedStatement preparedStatement= connect.prepareStatement(GET_PAGE)){
            preparedStatement.setInt(1, page.getLinesPage());
            preparedStatement.setInt(2, page.getFirstLine()-1);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()){
            	Company company = CompanyMapper.mapResultSet(result);
            	companies.add(company);
            }
            result.close();
        } catch (SQLException e) {
            logger.error("Error when listing the companies on a page",e);
        }
		return companies;
	}
}
