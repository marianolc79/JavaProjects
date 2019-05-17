package test.batch.processors;

import org.springframework.batch.item.ItemProcessor;

import test.batch.entities.IdCollection;
import test.batch.entities.PerfTestPojo;

public class PojoMongoItemProcessor implements ItemProcessor<PerfTestPojo, IdCollection> {

	@Override
	public IdCollection process(PerfTestPojo item) throws Exception {
		return new IdCollection(item.getId());
	}

}
