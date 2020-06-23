package com.excilys.java.persistence.DAO;

import java.io.FileInputStream;
import java.util.List;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;

import com.excilys.java.model.Company;


public class ComputerDAOTest extends DBTestCase {
	
	private static CompanyDAO companyDAO = CompanyDAO.getInstance();
	private static final String DB_FILE = "src/test/resources/dbTest.xml";
	
	public ComputerDAOTest(String name) {
		super(name);
		System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.cj.jdbc.Driver");
	    System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,"jdbc:mysql://localhost:3306/computer-database-test?serverTimezone=Europe/Paris");
	    System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "adminTest");
	    System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,"test");
	}
	
	/*public ComputerDAOTest(String name) {
		 super(name);
		 try {
				Properties properties = new Properties();
				InputStream input = new FileInputStream(DB_PROPERTIES);
				properties.load(input);
				System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, properties.getProperty("jdbc.driver"));
			    System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, properties.getProperty("jdbc.url"));
			    System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, properties.getProperty("jdbc.user"));
			    System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,properties.getProperty("jdbc.password"));
	            input.close();
	            Class.forName(driver);
			    connection = DriverManager.getConnection( url, user, password );	

			} catch (IOException e) {
				e.printStackTrace();
				logger.error("Error during the connexion with MySQL",e);
			}
	}*/

	@Test
	public void testExist() {
		
		fail("Not yet implemented");
	}

	@Test
	public void testCount() {
		fail("Not yet implemented");
	}

	@Test
	public void testComputerDAO() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetInstance() {
	    assertNotNull(companyDAO);
	    assertEquals(CompanyDAO.getInstance(), companyDAO);
	}

	@Test
	public void testGetAll() {
		List<Company> companies = companyDAO.getAll();
		System.out.println(companies.size());
		assertEquals(3,companies.size());
	}

	@Test
	public void testFindByIdLong() {
		
		fail("Not yet implemented");
	}

	@Test
	public void testCreate() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPagePage() {
		fail("Not yet implemented");
	}
	
	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream(DB_FILE));
	}
	
	protected DatabaseOperation getSetUpOperation() throws Exception
    {
        return DatabaseOperation.REFRESH;
    }

    protected DatabaseOperation getTearDownOperation() throws Exception
    {
        return DatabaseOperation.NONE;
    }

}
