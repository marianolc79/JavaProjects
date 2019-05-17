package entities;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-05-05T22:11:55.311+0200")
@StaticMetamodel(JobHistory.class)
public class JobHistory_ {
	public static volatile SingularAttribute<JobHistory, JobHistoryPK> id;
	public static volatile SingularAttribute<JobHistory, Date> endDate;
	public static volatile SingularAttribute<JobHistory, Department> department;
	public static volatile SingularAttribute<JobHistory, Employee> employee;
	public static volatile SingularAttribute<JobHistory, Job> job;
}
