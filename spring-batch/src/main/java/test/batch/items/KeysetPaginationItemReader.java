package test.batch.items;

import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import test.batch.entities.PerfTestPojo;

public class KeysetPaginationItemReader implements ItemReader<List<PerfTestPojo>> {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String lastId;

	@Override
	public List<PerfTestPojo> read()
			throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		String query;
		if (lastId != null) {
			query = "SELECT * FROM perftest WHERE id > '" + lastId + "' ORDER BY id LIMIT 1000";
		} else {
			query = "SELECT * FROM perftest ORDER BY id LIMIT 1000";
		}

		List<PerfTestPojo> list = jdbcTemplate.queryForList(query, PerfTestPojo.class);
		if (!list.isEmpty()) {
			lastId = list.get(list.size() - 1).getId();
			System.out.println("Last id:" + lastId);
			return list;
		} else {
			return null;
		}
	}

}
