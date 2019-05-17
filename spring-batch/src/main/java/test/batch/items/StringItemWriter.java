package test.batch.items;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class StringItemWriter implements ItemWriter<String> {

	@Override
	public void write(List<? extends String> items) throws Exception {

		for (String it : items) {
			System.out.println(it);
		}
	}

}
