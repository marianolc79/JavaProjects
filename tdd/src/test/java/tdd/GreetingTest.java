package tdd;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GreetingTest {

	private Greeting greeting;
	private IDateHelper dateHelper;

	@BeforeEach
	public void setUp() {
		dateHelper = new DateHelperMock(buildDateTime(6, 0));
		greeting = new Greeting(dateHelper);
	}

	private Date buildDateTime(int hour, int minute) {
		LocalDateTime localDateTime = LocalDateTime.of(2018, 1, 1, hour, minute, 0);
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	@Test
	void test1() {
		String result = greeting.hi("Benoit");
		assertThat(result, equalTo("Bonjour Benoit"));
	}

	@Test
	void test2() {
		String result = greeting.hi("  Benoit  ");
		assertThat(result, equalTo("Bonjour Benoit"));
	}

	@Test
	void test3() {
		String result = greeting.hi("  benoit  ");
		assertThat(result, equalTo("Bonjour Benoit"));
	}

	@Test
	void test4() {
		dateHelper = new DateHelperMock(buildDateTime(18, 0));
		greeting = new Greeting(dateHelper);

		String result = greeting.hi("benoit");
		assertThat(result, equalTo("Bonsoir Benoit"));
	}

	@Test
	void test5() {
		dateHelper = new DateHelperMock(buildDateTime(23, 00));
		greeting = new Greeting(dateHelper);

		String result = greeting.hi("benoit");
		assertThat(result, equalTo("Bonne nuit Benoit"));
	}
}
