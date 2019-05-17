package com.mkyong;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.dbutils.DbUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.batch.entities.PerfTestPojo;

public class TestRandomData {
	private Connection connection;
	private Random rnd;

	@Before
	public void setupDB() throws Exception {
		Class.forName("org.mariadb.jdbc.Driver");
		String db = "jdbc:mariadb://localhost:3306/test?user=root&password=";
		connection = DriverManager.getConnection(db);
		rnd = new Random(System.currentTimeMillis());
	}

	@After
	public void closeBD() {
		DbUtils.closeQuietly(connection);
	}

	public LocalDate getRandomDate() {
		int nDays = rnd.nextInt() % 50;
		return LocalDate.now().minusDays(nDays);
	}

	public String getRandomString() {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 50;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		return buffer.toString().toUpperCase();
	}

	@Test
	public void testRandomData() throws SQLException {

		String SQL = "INSERT INTO perftest (id, name, date) VALUES (?,?,?)";
		int row = 1;
		for (int i = 0; i < 1000000; i++) {
			PerfTestPojo pojo = new PerfTestPojo();

			pojo.setDate(getRandomDate());
			pojo.setName(getRandomString());
			PreparedStatement st = connection.prepareStatement(SQL);
			st.setString(1, UUID.randomUUID().toString());
			st.setString(2, pojo.getName());
			st.setObject(3, pojo.getDate());
			System.out.println("Row " + row++ + " generated");
			st.execute();
		}
	}
}
