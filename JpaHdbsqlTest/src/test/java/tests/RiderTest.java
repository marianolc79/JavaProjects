package tests;

import static com.github.database.rider.core.util.EntityManagerProvider.em;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.Persistence;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.SeedStrategy;
import com.github.database.rider.core.util.EntityManagerProvider;

import entities.Country;

@RunWith(JUnit4.class)
@DataSet(value = { "dataset.xml" }, strategy = SeedStrategy.INSERT,
disableConstraints = true, cleanBefore= true, cleanAfter = true)
public class RiderTest  {

	@Rule
	public EntityManagerProvider emProvider = EntityManagerProvider.instance("JpaHdbsqlTest");

	
	
	
	@Rule
	public DBUnitRule dbUnitRule = DBUnitRule.instance(emProvider.connection());


    @BeforeClass
    public static void initDB() {
        //trigger db initialization
        Persistence.createEntityManagerFactory("JpaHdbsqlTest");
    }
	
	
	@Test
	public void test1() {
		List<Country> countries = em().createQuery("select c from Country c").getResultList();
		assertTrue(countries.size() > 0);
	}
	@Test
	public void test2() {
		List<Country> countries = em().createQuery("select c from Country c").getResultList();
		assertTrue(countries.size() > 0);
	}

	@Test
	public void test3() {
		List<Country> countries = em().createQuery("select c from Country c").getResultList();
		assertTrue(countries.size() > 0);
	}

}
