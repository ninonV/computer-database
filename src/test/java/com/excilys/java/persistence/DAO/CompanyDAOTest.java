package com.excilys.java.persistence.DAO;

import java.io.FileInputStream;
import java.util.List;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.java.model.Company;
import com.excilys.java.model.Page;

public class CompanyDAOTest extends DBTestCase {
	
	@Autowired
	private static CompanyDAO companyDAO;
	private static final String DB_FILE = "src/test/resources/dbTest.xml";
	
	public CompanyDAOTest(String name) {
		super(name);
		System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.cj.jdbc.Driver");
	    System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,"jdbc:mysql://localhost:3306/computer-database-test?serverTimezone=Europe/Paris");
	    System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "adminTest");
	    System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,"test");
	}
	
	@Test
	public void testExist() {
		assertTrue(companyDAO.exist(1L));
	    assertFalse(companyDAO.exist(8L));
	}

	@Test
	public void testCount() {
		assertEquals(3, companyDAO.count());
	}

	@Test
	public void testGetAll() {
		List<Company> companies = companyDAO.getAll();
		assertEquals(3,companies.size());
	}

	@Test
	public void testFindByIdLong() {
		Long id = 1L;
		Company company = companyDAO.findById(id);
		assertTrue(companyDAO.exist(id));
		assertEquals(id,company.getId());
		assertEquals("Apple Inc.",company.getName());
	}
	
	@Test
	public void testGetPage() {
		Page page = new Page();
		page.setLinesPage(2);
		List<Company> companies = companyDAO.getPage(page);
		assertEquals(2,companies.size());
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream(DB_FILE));
	}
	
	protected DatabaseOperation getSetUpOperation() throws Exception{
        return DatabaseOperation.REFRESH;
    }

    protected DatabaseOperation getTearDownOperation() throws Exception{
        return DatabaseOperation.NONE;
    }

}
