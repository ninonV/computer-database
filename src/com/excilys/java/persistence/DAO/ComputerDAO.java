package com.excilys.java.persistence.DAO;
import com.excilys.java.mapper.ComputerMapper;
import com.excilys.java.model.Computer;
import com.excilys.java.model.Page;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

/**
 *  Class doing the relation with the table computer  
 *  @author ninonV
 *  **/

public class ComputerDAO extends DAO<Computer>{
	
	private static final String GET_ALL = "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.name AS company_name FROM computer LEFT JOIN company ON company_id = company.id ORDER BY computer.id ";
	private static final String GET_WITH_ID = "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.name AS company_name FROM computer LEFT JOIN company ON company_id = company.id WHERE computer.id = ? ";
	private static final String CREATE = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,?)";
	private static final String UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id= ?";
	private static final String DELETE = "DELETE FROM computer WHERE id = ? ";
	private static final String COUNT = "SELECT COUNT(*) FROM computer";
	private static final String GET_PAGE = "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.name AS company_name FROM computer LEFT JOIN company ON company_id = company.id LIMIT ? OFFSET ?";
	
	private static ComputerDAO computerDAO;
	
	public ComputerDAO() {
	}
	
	/**
     * Create the instance of computerDAO if it not exists
     * @return computerDAO
     */

	public static ComputerDAO getInstance() {
		if (computerDAO == null) {
            computerDAO = new ComputerDAO();
        }
        return computerDAO;
    }

	
	
	@Override
	public ArrayList<Computer> getAll() {
		ArrayList<Computer> computers = new ArrayList();
		try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(GET_ALL);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()){
            	Computer computer = ComputerMapper.map(result);
            	computers.add(computer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		System.out.println(computers);
		return computers;	
	
	}
	@Override
	public Computer findById(Long id) {
		PreparedStatement preparedStatement = null; 
		Computer computer = new Computer();
		if(id!=null) {
			try {
	            preparedStatement = this.connection.prepareStatement(GET_WITH_ID);
	            preparedStatement.setLong(1, id);
	            ResultSet result = preparedStatement.executeQuery();
	            while (result.next()){
	            	computer = ComputerMapper.map(result);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		}
		System.out.println(computer);
		return computer;
	}
	
	/**
	 * Add a computer
	 * @param computer
	 */
	
	public void create(Computer computer) {
		try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(CREATE);
            preparedStatement.setString(1, computer.getName());
            preparedStatement.setDate(2, computer.getIntroduced());
            preparedStatement.setDate(3, computer.getDiscontinued());
            if (computer.getManufacturer().getIdCompany()==null) {
            	preparedStatement.setNull(4, Types.BIGINT);
            }else {
            	 preparedStatement.setLong(4, computer.getManufacturer().getIdCompany());
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Update an existing computer
	 * @param computer
	 */
	
	public void update(Computer computer) {
		try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, computer.getName());
            preparedStatement.setDate(2, computer.getIntroduced());
            preparedStatement.setDate(3, computer.getDiscontinued());
            if (computer.getManufacturer().getIdCompany()==null) {
            	preparedStatement.setNull(4, Types.BIGINT);
            }else {
            	 preparedStatement.setLong(4, computer.getManufacturer().getIdCompany());
            }
            preparedStatement.setLong(5, computer.getIdComputer());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Delete an existing computer
	 * @param computer
	 */
	
	public void delete(Long id) {
		try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(DELETE);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }		
	}
	
	@Override
	public boolean exist(Long id){
		boolean isInBDD = false; 
		if (this.findById(id)!=null) {
			isInBDD=true; 
		}
		return isInBDD; 
	}

	@Override
	public int count() {
		int total = 0;
		try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(COUNT);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            total = result.getInt(1);
		 } catch (SQLException e) {
	            e.printStackTrace();
	        }
            return total; 
	}

	@Override
	public ArrayList<Computer> getPage(Page page) {
		ArrayList<Computer> computers= new ArrayList();
		try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(GET_PAGE);
            preparedStatement.setInt(1, page.getLinesPage());
            preparedStatement.setInt(2, page.getFirstLine()-1);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()){
            	Computer computer = ComputerMapper.map(result);
            	computers.add(computer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		System.out.println(computers);
		return computers;
	}
}
