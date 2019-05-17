package perf;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

public class MyTest {
	@Rule
	public TestName testName = new TestName();

	@Test
	public void test1() {
		System.out.println("test1");
	}

	@Test
	public void test2() {
		System.out.println("test2");

	}

	@Before
	public void setUp() {
		if ("test1".equals(testName.getMethodName())) {
			System.out.println("setUp1");
		} else {
			System.out.println("setUp2");
		}
	}

}
