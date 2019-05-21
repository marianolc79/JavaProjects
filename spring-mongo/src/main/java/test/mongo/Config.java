package test.mongo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

@Configuration
@ComponentScan
@EnableMongoRepositories(basePackages = "test.mongo.repositories")
public class Config {

	@Bean(name = "mongoTemplate")
	MongoTemplate mongoTemplate() {
		MongoClient client = new MongoClient();
		MongoTemplate mongoTemplate = new MongoTemplate(client, "tests");
		return mongoTemplate;
	}

}
