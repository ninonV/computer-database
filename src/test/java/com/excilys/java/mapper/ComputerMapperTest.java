package com.excilys.java.mapper;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.excilys.java.model.Computer;
import com.excilys.java.model.Company;

public class ComputerMapperTest {

	private static final String ID_COMPUTER = "id";
    private static final String NAME_COMPUTER = "name";
    private static final String DATE_INTRODUCED = "introduced";
    private static final String DATE_DISCONTINUED = "discontinued";
    private static final String ID_COMPANY = "company_id";
    private static final String NAME_COMPANY = "company_name";
    
	
	private final Long id = 100L;
	private final String name = "Computer name";
	private final Date introduced = Date.valueOf("1970-06-10");
	private final Date discontinued = Date.valueOf("1990-01-07");
	private final Long idCompany = 20L; 
	private final String nameCompany = "Company name";
	
	private ResultSet resultSet = Mockito.mock(ResultSet.class);
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void test() throws Exception {
		try {
			Mockito.when(resultSet.getLong(ID_COMPUTER)).thenReturn(id);
			Mockito.when(resultSet.getString(NAME_COMPUTER)).thenReturn(name);
			Mockito.when(resultSet.getDate(DATE_INTRODUCED)).thenReturn(introduced);
			Mockito.when(resultSet.getDate(DATE_DISCONTINUED)).thenReturn(discontinued);
			Mockito.when(resultSet.getLong(ID_COMPANY)).thenReturn(idCompany);
			Mockito.when(resultSet.getString(NAME_COMPANY)).thenReturn(nameCompany);
		} catch (SQLException e) {
			fail("Fail to construct ResultSet");
		}
		
		Computer computer = new Computer(name,introduced.toLocalDate(),discontinued.toLocalDate(), new Company(idCompany,nameCompany));
		computer.setIdComputer(id);
		Computer computerMapper = ComputerMapper.map(resultSet);
		assertEquals(computer,computerMapper);
	}
	
	@Test
	public void testIntroducedNull() throws Exception {
		try {
			Mockito.when(resultSet.getLong(ID_COMPUTER)).thenReturn(id);
			Mockito.when(resultSet.getString(NAME_COMPUTER)).thenReturn(name);
			Mockito.when(resultSet.getDate(DATE_DISCONTINUED)).thenReturn(discontinued);
			Mockito.when(resultSet.getLong(ID_COMPANY)).thenReturn(idCompany);
			Mockito.when(resultSet.getString(NAME_COMPANY)).thenReturn(nameCompany);
		} catch (SQLException e) {
			fail("Fail to construct ResultSet");
		}
		
		Computer computer = new Computer(name,null,discontinued.toLocalDate(), new Company(idCompany,nameCompany));
		computer.setIdComputer(id);
		Computer computerMapper = ComputerMapper.map(resultSet);
		assertEquals(computer,computerMapper);
	}
	
	@Test
	public void testDiscontinuedNull() throws Exception {
		try {
			Mockito.when(resultSet.getLong(ID_COMPUTER)).thenReturn(id);
			Mockito.when(resultSet.getString(NAME_COMPUTER)).thenReturn(name);
			Mockito.when(resultSet.getDate(DATE_INTRODUCED)).thenReturn(introduced);
			Mockito.when(resultSet.getLong(ID_COMPANY)).thenReturn(idCompany);
			Mockito.when(resultSet.getString(NAME_COMPANY)).thenReturn(nameCompany);
		} catch (SQLException e) {
			fail("Fail to construct ResultSet");
		}
		
		Computer computer = new Computer(name,introduced.toLocalDate(),null, new Company(idCompany,nameCompany));
		computer.setIdComputer(id);
		Computer computerMapper = ComputerMapper.map(resultSet);
		assertEquals(computer,computerMapper);
	}
	

	@Test
	public void testCompanyNull() throws Exception {
		try {
			Mockito.when(resultSet.getLong(ID_COMPUTER)).thenReturn(id);
			Mockito.when(resultSet.getString(NAME_COMPUTER)).thenReturn(name);
			Mockito.when(resultSet.getDate(DATE_INTRODUCED)).thenReturn(introduced);
			Mockito.when(resultSet.getDate(DATE_DISCONTINUED)).thenReturn(discontinued);
		} catch (SQLException e) {
			fail("Fail to construct ResultSet");
		}
		
		Computer computer = new Computer(name,introduced.toLocalDate(),discontinued.toLocalDate(), new Company());
		System.out.println(computer.getManufacturer());
		computer.setIdComputer(id);
		Computer computerMapper = ComputerMapper.map(resultSet);
		assertEquals(computer,computerMapper);
	}
}
