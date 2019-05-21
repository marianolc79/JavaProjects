package test.mongo.repositories;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import test.mongo.entities.TestPojo;

@Component
public class CustomRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	public List<TestPojo> getAll() {
		return mongoTemplate.findAll(TestPojo.class);
	}

	public void insertNested(String code, List<TestPojo> list) {
		Query query = new Query(where("code").is(code));

		Update upd = new Update();
		upd.push("nested").each(list.toArray());
		upd.set("code", code);

		mongoTemplate.upsert(query, upd, TestPojo.class);
	}
}
