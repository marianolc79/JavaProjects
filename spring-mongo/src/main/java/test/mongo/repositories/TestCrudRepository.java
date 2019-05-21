package test.mongo.repositories;

import org.springframework.data.repository.CrudRepository;

import test.mongo.entities.TestPojo;

public interface TestCrudRepository extends CrudRepository<TestPojo, Integer> {

}
