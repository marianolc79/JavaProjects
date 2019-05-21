package test.batch.items;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class NoOpWriter implements ItemWriter<Object> {
	public void write(List<? extends Object> items) throws java.lang.Exception {
	}
}
