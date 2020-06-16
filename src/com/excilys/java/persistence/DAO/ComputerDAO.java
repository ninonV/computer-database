package com.excilys.java.persistence.DAO;
import com.excilys.java.mapper.ComputerMapper;
import com.excilys.java.model.Company;
import com.excilys.java.model.Computer;
import com.excilys.java.persistence.MysqlConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;

public class ComputerDAO extends DAO<Computer>{
	
	private static final String SELECT_ALL = "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.name AS company_name FROM computer LEFT JOIN company ON company_id = company.id ORDER BY id ";
	private static final String SELECT_WITH_ID = "SELECT computer.id, computer.name, introduced, discontinued, company_id, company.name AS company_name FROM computer LEFT JOIN company ON company_id = company.id WHERE computer.id = ? ";
	private static final String CREATE = "INSERT INTO computer (id, name, introduced, discontinued, company_id) VALUES (?,?,?,?,?)";
	private static final String UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id= ?";
	private static final String DELETE = "DELETE FROM computer WHERE id = ? ";
	
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
            PreparedStatement preparedStatement = this.connection.prepareStatement(SELECT_ALL);
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
	            preparedStatement = this.connection.prepareStatement(SELECT_WITH_ID);
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
            preparedStatement.setLong(1, computer.getIdComputer());
            preparedStatement.setString(2, computer.getName());
            preparedStatement.setDate(3, computer.getIntroduced());
            preparedStatement.setDate(4, computer.getDiscontinued());
            preparedStatement.setLong(5, computer.getManufacturer().getIdCompany());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * Update an existing ciomputer
	 * @param computer
	 */
	
	public void update(Computer computer) {
		try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, computer.getName());
            preparedStatement.setDate(2, computer.getIntroduced());
            preparedStatement.setDate(3, computer.getDiscontinued());
            preparedStatement.setLong(4, computer.getManufacturer().getIdCompany());
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
}
