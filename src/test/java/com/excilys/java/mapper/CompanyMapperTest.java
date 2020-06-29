package com.excilys.java.mapper;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import com.excilys.java.model.Company;

public class CompanyMapperTest {
	
	private static final String ID_COMPANY = "id";
    private static final String NAME_COMPANY = "name";
	
	private final Long id = 10L;
	private final String name = "Company name";
	
	private ResultSet resultSet = Mockito.mock(ResultSet.class);
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testMap() {
		try {
			Mockito.when(resultSet.getLong(ID_COMPANY)).thenReturn(id);
			Mockito.when(resultSet.getString(NAME_COMPANY)).thenReturn(name);
		} catch (SQLException e) {
			fail("Fail to construct ResultSet");
		}
		Company company = new Company(id,name);
		Company companyMapper = CompanyMapper.mapResultSet(resultSet);
		assertEquals(company, companyMapper);
	}

}
