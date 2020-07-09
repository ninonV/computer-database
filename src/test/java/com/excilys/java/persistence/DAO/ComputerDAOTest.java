package com.excilys.java.persistence.DAO;

import java.io.FileInputStream;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import org.dbunit.DBTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.java.Spring.SpringConfiguration;
import com.excilys.java.model.Company;
import com.excilys.java.model.Computer;
import com.excilys.java.model.Page;
import com.excilys.java.persistence.HikariConnect;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfiguration.class})
public class ComputerDAOTest extends DBTestCase {
	
	@Autowired
	private ComputerDAO computerDAO;
	@Autowired
	private HikariConnect hikariConnect;
	
	private static final String DB_FILE = "src/test/resources/dbTest.xml";

	@Before
	public void setUp() throws Exception {
		try(Connection connection = hikariConnect.getConnection()){
			DatabaseConnection jUnitConnect = new DatabaseConnection(connection);
			getSetUpOperation().execute(jUnitConnect, getDataSet());
		}
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
		Long id = 608L;
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
	

}
