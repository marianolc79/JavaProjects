package test.mongo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import test.mongo.entities.TestPojo;
import test.mongo.repositories.CustomRepository;
import test.mongo.repositories.TestCrudRepository;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Config.class)
public class MongoTests {

	@Autowired
	MongoTemplate template;
	@Autowired
	TestCrudRepository testCrudRepository;
	@Autowired
	CustomRepository testCustomRepository;

	@Test
	public void testIOC() {
		Assert.assertNotNull(template);
		Assert.assertNotNull(testCrudRepository);
		Assert.assertNotNull(testCustomRepository);
	}

	@Test
	public void testGetAll() {
		List<TestPojo> list = testCustomRepository.getAll();
		Assert.assertTrue(list.size() > 0);
	}

	@Test
	public void update() {
		TestPojo p1 = new TestPojo();
		p1.setId(ObjectId.get());
		p1.setName("test");
		p1.setDate(new Date());
		List<TestPojo> list = new ArrayList<>();
		list.add(p1);
		testCustomRepository.insertNested("code", list);
	}
}
