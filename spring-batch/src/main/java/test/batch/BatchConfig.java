package test.batch;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import test.batch.entities.PerfTestPojo;

@Configuration
public class BatchConfig {
	private static final String QUERY = "SELECT * FROM perftest LIMIT 10000";

	@Bean(name = "itemReader")
	ItemReader<PerfTestPojo> databaseXmlItemReader(DataSource dataSource) {
		JdbcCursorItemReader<PerfTestPojo> databaseReader = new JdbcCursorItemReader<>();

		databaseReader.setDataSource(dataSource);
		databaseReader.setSql(QUERY);
		databaseReader.setFetchSize(1000);
		databaseReader.setRowMapper(new BeanPropertyRowMapper<>(PerfTestPojo.class));
		databaseReader.setVerifyCursorPosition(false);

		return databaseReader;
	}

//	<bean id="mongoIdItemReader"
//			class="org.springframework.batch.item.data.MongoItemReader">
//			<property name="template" ref="mongoTemplate" />
//			<property name="collection" value="testIds" />
//			<property name="fields" value="_id" />
//			<property name="query" value="{}" />
//			<property name="targetType" value="java.lang.String" />
//			<property name="sort">
//				<util:map>
//					<entry key="id_"
//						value="#{T(org.springframework.data.domain.Sort.Direction).ASC}" />
//				</util:map>
//			</property>
//		</bean>

	@Bean(name = "mongoIdItemReader")
	ItemReader<String> mongoItemReader(MongoTemplate template) {
		MongoItemReader<String> mongoItemReader = new MongoItemReader<>();

		mongoItemReader.setTemplate(template);
		mongoItemReader.setQuery("{}");
		mongoItemReader.setFields("{id_:1}");
		mongoItemReader.setTargetType(String.class);
		Map<String, Direction> sort = new HashMap<String, Direction>();
		sort.put("id_", Direction.ASC);
		mongoItemReader.setSort(sort);
		return mongoItemReader;
	}

}
