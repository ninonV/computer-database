package com.excilys.java.persistence.DAO;

import java.io.FileInputStream;
import java.sql.Connection;
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
import com.excilys.java.model.Page;
import com.excilys.java.persistence.HikariConnect;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfiguration.class})
public class CompanyDAOTest extends DBTestCase {
	
	@Autowired
	private CompanyDAO companyDAO;
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
		Company companyTest = companyDAO.findById(id);
		assertTrue(companyDAO.exist(id));
		assertEquals(id,companyTest.getId());
		assertEquals("Apple Inc.",companyTest.getName());
	}
	
	@Test
	public void testGetPage() {
		Page page = new Page();
		page.setLinesPage(2);
		List<Company> companies = companyDAO.getPage(page);
		assertEquals(2,companies.size());
	}

}
