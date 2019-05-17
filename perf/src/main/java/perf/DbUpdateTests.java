package perf;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.dbutils.AsyncQueryRunner;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DbUpdateTests {
	private static final Logger logger = LogManager.getLogger(DbUpdateTests.class);

	private static final int NUM_DATA = 1000000;

	private static final String DDL_CREATE = "CREATE TABLE TEST_TABLE (ID NUMBER NOT NULL, NAME VARCHAR2(20) NOT NULL, DESCRIPTION VARCHAR2(1000) NOT NULL, INJECTED NUMBER(1), PRIMARY KEY (ID))";
	private static final String DELETE_DATA = "DELETE FROM TEST_TABLE";
	private static final String INSERT_DATA = "INSERT INTO TEST_TABLE VALUES (?, ?, ?)";

	private static void createTable() throws SQLException {

		QueryRunner run = new QueryRunner(DataSourceProvider.getInstance().getDataSource());
		run.execute(DDL_CREATE);

		// finally {
		// DbUtils.closeQuietly(con);
		// }
	}

	private static Object[] getRandomRow(int cnt) {
		return new Object[] { new Integer(cnt), RandomData.generateRandomString(20),
				RandomData.generateRandomString(500) };
	}

	private static void fillDataBatchMode(QueryRunner run, int commitInterval) throws SQLException {
		long start = System.currentTimeMillis();
		List<Object[]> paramsList = new ArrayList<>();
		try {

			for (int i = 0; i < NUM_DATA; i++) {
				paramsList.add(getRandomRow(i + 1));
				if ((i + 1) % commitInterval == 0) {
					Object[][] param;
					try {
						param = new Object[paramsList.size()][];
						paramsList.toArray(param);
						paramsList.clear();
						run.batch(INSERT_DATA, param);
						logger.info((i + 1) + " rows inserted...");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			logger.info("Data loading finished... "
					+ DurationFormatUtils.formatDurationHMS(System.currentTimeMillis() - start));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void fillDataNormal(QueryRunner run, int commitInterval) throws SQLException {
		long start = System.currentTimeMillis();

		for (int i = 0; i < NUM_DATA; i++) {
			run.execute(INSERT_DATA, getRandomRow(i + 1));
			// if ((i + 1) % commitInterval == 0) {
			// run.con.commit();
			// }

			if ((i + 1) % 1000 == 0) {
				logger.info((i + 1) + " rows inserted...");
			}
		}
		// con.commit();
		logger.info("Data loading finished... "
				+ DurationFormatUtils.formatDurationHMS(System.currentTimeMillis() - start));
	}

	private static void fillData(int commitInterval, boolean batchMode, boolean asyncMode) throws SQLException {
		QueryRunner run = new QueryRunner(DataSourceProvider.getInstance().getDataSource());
		run.execute(DELETE_DATA);
		// con.commit();

		if (batchMode) {
			fillDataBatchMode(run, commitInterval);
		} else if (asyncMode) {
			fillDataAsyncMode(commitInterval);
		} else {
			fillDataNormal(run, commitInterval);

		}
		// finally {
		// DbUtils.closeQuietly(con);
		// }
	}

	private static void fillDataAsyncMode(int commitInterval) throws SQLException {
		long start = System.currentTimeMillis();
		List<Object[]> paramsList = new ArrayList<>();

		ExecutorService executorService = Executors.newFixedThreadPool(10);
		QueryRunner qr = new QueryRunner(DataSourceProvider.getInstance().getDataSource());
		AsyncQueryRunner runner = new AsyncQueryRunner(executorService, qr);

		for (int i = 0; i < NUM_DATA; i++) {
			paramsList.add(getRandomRow(i + 1));
			if ((i + 1) % commitInterval == 0) {
				Object[][] param = new Object[paramsList.size()][];
				paramsList.toArray(param);
				paramsList.clear();
				Future<int[]> future = runner.batch(INSERT_DATA, param);
				executorService.submit(new Runnable() {

					@Override
					public void run() {
						try {
							int[] results = future.get();
							System.out.println("Result done!!");
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				});

				logger.info((i + 1) + " rows inserted...");
			}
		}
		executorService.shutdown();
		logger.info("Data loading finished... "
				+ DurationFormatUtils.formatDurationHMS(System.currentTimeMillis() - start));

		// try {
		//
		// for (int i = 0; i < NUM_DATA; i++) {
		// paramsList.add(getRandomRow(i + 1));
		//
		// if ((i + 1) % commitInterval == 0) {
		// Object[][] param = new Object[paramsList.size()][];
		// paramsList.toArray(param);
		// paramsList.clear();
		// Connection con = null;
		// try {
		// con = DataSourceProvider.getConnection();
		// Future<V> future = runner.batch(con, INSERT_DATA, param);
		// con.commit();
		// logger.info((i + 1) + " rows inserted...");
		// } finally {
		// DbUtils.closeQuietly(con);
		// }
		//
		// }
		// }
		// logger.info("Data loading finished... "
		// + DurationFormatUtils.formatDurationHMS(System.currentTimeMillis() - start));
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

	public static void main(String[] args) throws Exception {
		// createTable();
		fillData(1000, true, false);
	}

}
