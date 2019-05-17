package perf;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.StatementConfiguration;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang.time.DurationFormatUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.impl.ConcurrentUpdateSolrClient;
import org.apache.solr.client.solrj.request.UpdateRequest;
import org.apache.solr.common.SolrInputDocument;

public class DbSelectTests {
	private static final Logger logger = LogManager.getLogger(DbSelectTests.class);

	private static final int NUM_DATA = 100000;

	private static final String SELECT_COUNT = "SELECT COUNT(*) cnt FROM TEST_TABLE";
	private static final String SELECT_DATA_BETWEEN = "SELECT \"ID\", NAME, DESCRIPTION FROM TEST_TABLE WHERE \"ID\" BETWEEN ? AND ?";
	private static final String UPDATE_DATA = "UPDATE TEST_TABLE SET \"INJECTED\" = 1 WHERE \"ID\" BETWEEN ? AND ?";

	private static List<MyBean> getData(int start, int end) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceProvider.getInstance().getDataSource());

		Map<String, String> map = new HashMap<>();
		map.put("id", "id");
		map.put("name", "name");
		map.put("description", "description");
		return runner.query(SELECT_DATA_BETWEEN,
				new BeanListHandler<MyBean>(MyBean.class, new BasicRowProcessor(new BeanProcessor(map))), start, end);
	}
	
	private static List<SolrInputDocument> getDataSolrDoc(int start, int end) throws SQLException {
		
		StatementConfiguration sc = new StatementConfiguration(null, NUM_DATA, null, NUM_DATA, null);
		
		QueryRunner runner = new QueryRunner(DataSourceProvider.getInstance().getDataSource(), sc);
		
		ResultSetHandler<List<SolrInputDocument>> rsh = new ResultSetHandler<List<SolrInputDocument>>() {
			@Override
			public List<SolrInputDocument> handle(ResultSet rs) throws SQLException {
				List<SolrInputDocument> list = new ArrayList<>();
				while(rs.next()) {
					SolrInputDocument doc = new SolrInputDocument();
					doc.setField("id", rs.getInt("id"));
					doc.setField("name", rs.getString("name"));
					doc.setField("description", rs.getString("description"));
					list.add(doc);
				}
				return list;
			}
	
		};
		return runner.query(SELECT_DATA_BETWEEN,
				rsh, start, end);
	}

	private static int getCount() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceProvider.getInstance().getDataSource());
		return runner.query(SELECT_COUNT, new ScalarHandler<Integer>("cnt") {
			@Override
			public Integer handle(ResultSet rs) throws SQLException {
				if (rs.next()) {
					return rs.getInt("cnt");
				}
				return null;
			}

		});
	}

	private static int calculIterations(int total) {
		return total / NUM_DATA + (total % NUM_DATA != 0 ? 1 : 0);
	}

	private static int[] calculIndexes(int total, int totalIterations, int nIteration) {
		int[] indexes = new int[2];
		indexes[0] = nIteration * NUM_DATA + 1;

		if (nIteration == totalIterations - 1) {
			indexes[1] = total;
		} else {
			indexes[1] = indexes[0] + NUM_DATA - 1;
		}
		return indexes;
	}

	private static void scanSequence(int total, int iterations) throws Exception {
		long start = System.currentTimeMillis();
		for (int i = 0; i < iterations; i++) {
			int[] indexes = calculIndexes(total, iterations, i);
			List<MyBean> list = getData(indexes[0], indexes[1]);
			logger.info("[" + indexes[0] + "," + indexes[1] + "]");
		}
		logger.info("Data loading finished... "
				+ DurationFormatUtils.formatDurationHMS(System.currentTimeMillis() - start));
	}
	
	private static void updateAsInjected(int start, int end) throws Exception {
		QueryRunner runner = new QueryRunner(DataSourceProvider.getInstance().getDataSource());
		runner.update(UPDATE_DATA, new Integer(start), new Integer(end));
	}

	private static void scanThread(int total, int iterations) throws Exception {
		ExecutorService service = Executors.newWorkStealingPool();
		//ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		
//		ConcurrentUpdateSolrClient server = new ConcurrentUpdateSolrClient.Builder("http://localhost:8983/solr/test2")
//				.withQueueSize(20)
//				.withThreadCount(10)
//				.build();
		
		long start = System.currentTimeMillis();
		for (int i = 0; i < iterations; i++) {
			int[] indexes = calculIndexes(total, iterations, i);
			service.submit(new Runnable() {
				@Override
				public void run() {
					try {
						logger.info("Init [" + indexes[0] + "," + indexes[1] + "]");
						List<MyBean> list = getData(indexes[0], indexes[1]);
//						server.addBeans(list);
//						updateAsInjected(indexes[0],indexes[1]);
						logger.info("[" + indexes[0] + "," + indexes[1] + "]");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
//		server.close();
//		service.shutdown();
		while(!service.isTerminated()) {}
		logger.info("Data loading finished... "
				+ DurationFormatUtils.formatDurationHMS(System.currentTimeMillis() - start));	
	}
	
	private static void scanThread2(int total, int iterations) throws Exception {
		ExecutorService service = Executors.newWorkStealingPool();
		//ExecutorService service = Executors.newFixedThreadPool(10);
		
//		ConcurrentUpdateSolrClient server = new ConcurrentUpdateSolrClient.Builder("http://localhost:8983/solr/test2")
//				.withQueueSize(10)
//				.withThreadCount(10)
//				.build();
		
		long start = System.currentTimeMillis();
		for (int i = 0; i < iterations; i++) {
			int[] indexes = calculIndexes(total, iterations, i);
			service.submit(new Runnable() {
				@Override
				public void run() {
					try {
						logger.info("Init [" + indexes[0] + "," + indexes[1] + "]");
						List<SolrInputDocument> list = getDataSolrDoc(indexes[0], indexes[1]);
//						UpdateRequest updateRequest = new UpdateRequest();
//					    updateRequest.add(list );
					    //server.request(updateRequest);

						//updateAsInjected(indexes[0],indexes[1]);
						logger.info("[" + indexes[0] + "," + indexes[1] + "]");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
//		server.close();
		service.shutdown();
		while(!service.isTerminated()) {}
		logger.info("Data loading finished... "
				+ DurationFormatUtils.formatDurationHMS(System.currentTimeMillis() - start));	
	}

	public static void main(String[] args) throws Exception {
		int total = getCount();
		logger.info("Total => " + total);
		int iterations = calculIterations(total);
		//scanSequence(total, iterations);
		scanThread2(total, iterations);
	}

}
