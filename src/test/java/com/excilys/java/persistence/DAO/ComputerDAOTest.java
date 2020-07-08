package com.excilys.java.persistence.DAO;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.List;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.java.model.Company;
import com.excilys.java.model.Computer;
import com.excilys.java.model.Page;


public class ComputerDAOTest extends DBTestCase {
	
	@Autowired
	private static ComputerDAO computerDAO;
	private static final String DB_FILE = "src/test/resources/dbTest.xml";
	
	public ComputerDAOTest(String name) {
		super(name);
		System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "com.mysql.cj.jdbc.Driver");
	    System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,"jdbc:mysql://localhost:3306/computer-database-test?serverTimezone=Europe/Paris");
	    System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "adminTest");
	    System.setProperty( PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,"test");
	}

	@Test
	public void testExist() {
		assertTrue(computerDAO.exist(6L));
	    assertFalse(computerDAO.exist(800L));
	}

	@Test
	public void testCount() {
		assertEquals(3, computerDAO.count(null));
	}


	@Test
	public void testGetAll() {
		List<Computer> computers = computerDAO.getAll();
		assertEquals(3,computers.size());
	}

	@Test
	public void testFindByIdLong() {
		Long id = 8L;
		Computer computer = computerDAO.findById(id);
		assertTrue(computerDAO.exist(id));
		assertEquals(id,computer.getId());
		assertEquals("Apple IIc",computer.getName());
	}

 	@Test
	public void testCreate() {
		Computer computer = new Computer.Builder()
				.setId(1L)
				.setName("Computer test created")
				.setIntroduced(LocalDate.parse("2019-08-24"))
				.setCompany(new Company.Builder().setId(1L).build())
				.build();

		computerDAO.create(computer);
		assertEquals(4, computerDAO.getAll().size());
	}

	@Test
	public void testUpdate() {
		Long id = 10L;
		Computer computer = computerDAO.findById(id);
		computer.setName("Computer test updated");
		computerDAO.update(computer);
		assertEquals(computer.getName(), (computerDAO.findById((id)).getName()));
	}

	@Test
	public void testDelete() {
		Long id = 605L;
		assertTrue(computerDAO.exist(id));
	    computerDAO.delete(id);
	    assertFalse(computerDAO.exist(id));
	}

	@Test
	public void testGetPage() {
		Page page = new Page();
		page.setLinesPage(2);
		List<Computer> computers = computerDAO.getPage(page,null,null);
		assertEquals(2,computers.size());
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
