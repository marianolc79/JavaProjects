package test.batch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class RecordCountTasklet implements Tasklet {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM perftest", Integer.class);
		System.out.println("Count = " + count);
		chunkContext.getStepContext().setAttribute("count", count);
		return RepeatStatus.FINISHED;
	}

}
