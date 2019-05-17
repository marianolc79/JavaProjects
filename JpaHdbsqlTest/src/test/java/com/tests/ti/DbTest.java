package com.tests.ti;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entities.Country;
import junit.framework.TestCase;

public class DbTest extends TestCase {
	
	private EntityManager em = null;
	public DbTest() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("JpaHdbsqlTest");
        em = factory.createEntityManager();
        
        //em.close();
	}
	
	
	@Before
	@Override
	public void setUp() {
		
	}

	@After
	public void after() throws Exception {
		//databaseTester.onTearDown();
	}

//	private static IDataSet getDataSet() throws Exception {
//		return new FlatXmlDataSetBuilder()
//				.build(DbTest.class.getClassLoader().getResourceAsStream("com/tests/ti/dataset.xml"));
//	}
//
//	private static DatabaseOperation getSetUpOperation() throws Exception {
//		return DatabaseOperation.REFRESH;
//	}
//
//	private static DatabaseOperation getTearDownOperation() throws Exception {
//		return DatabaseOperation.DELETE;
//	}

	@Test
	public void test1() {
		// Read the existing entries and write to console
        Query q = em.createQuery("SELECT c FROM Country c");
        List<Country> userList = q.getResultList();
        
        for (Country user : userList) {
             System.out.println(user.getCountryName());
        }
        System.out.println("Size: " + userList.size());

	}

	@Test
	public void test2() {

	}

	@Test
	public void test3() {

	}

	@Test
	public void test4() {

	}

}
