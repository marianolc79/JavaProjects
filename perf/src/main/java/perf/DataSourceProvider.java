package perf;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DataSourceProvider {

	private static final String pool = "hikari";

	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "PRUEBAS";
	private static final String PASSWORD = "PRUEBAS";
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";

	private static DataSourceProvider instance = new DataSourceProvider();

	private DataSource dataSource;

	private void configureHikari() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(URL);
		config.setUsername(USER);
		config.setPassword(PASSWORD);
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		// config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		config.setMaximumPoolSize(20);
		dataSource = new HikariDataSource(config);
	}

	private void configureDbcp() {
		// Create and configure DBCP DataSource
		BasicDataSource basicDs = new BasicDataSource();
		basicDs.setDriverClassName(DRIVER);
		basicDs.setUrl(URL);
		basicDs.setUsername(USER);
		basicDs.setPassword(PASSWORD);

		// Enable statement caching (Optional)
		basicDs.setPoolPreparedStatements(true);
		basicDs.setMaxOpenPreparedStatements(50);
		dataSource = basicDs;
	}

	private void configure() {
		if (pool.equals("dbcp")) {
			configureDbcp();
		} else {
			configureHikari();
		}
	}

	public Connection getConnection() {
		try {
			return getDataSource().getConnection();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public DataSource getDataSource() {
		if (dataSource == null) {
			configure();
		}
		return dataSource;
	}

	public static DataSourceProvider getInstance() {
		return instance;
	}
}
