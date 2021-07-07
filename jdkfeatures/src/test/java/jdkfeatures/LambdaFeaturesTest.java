package jdkfeatures;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class LambdaFeaturesTest {

	public class Employee {

		private Long numEmployee;
		private String name;
		private String lastName;

		public Employee(Long numEmployee, String name, String lastName) {
			this.numEmployee = numEmployee;
			this.name = name;
			this.lastName = lastName;
		}

		public Long getNumEmployee() {
			return numEmployee;
		}

		public void setNumEmployee(Long numEmployee) {
			this.numEmployee = numEmployee;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public void incNumEmployee() {
			this.numEmployee = this.numEmployee + 1;
		}
	}

	private List<String> stringList;
	private List<Employee> employeeList;

	@BeforeEach
	void setUp() throws Exception {
		stringList = Arrays.asList("60", "48", "10", "20", "15");
		employeeList = Arrays.asList(new Employee(1l, "A", "A"), new Employee(2l, "B", "B"), new Employee(3l, "C", "C"),
				new Employee(4l, "D", "D"));
		Collections.shuffle(stringList);
		Collections.shuffle(employeeList);
	}

	@Test
	@DisplayName("Runnable")
	public void runnableTest() throws Exception {
		Runnable runnable = () -> System.out.println("Awesome! Lambda Expression here!");
		runnable.run();
	}

	@Test
	@DisplayName("Sort")
	public void shouldOrderTheListOfNamesByLambdaExpressionWithoutParameterTypes() throws Exception {
		List<String> craftCoderGuides = Arrays.asList("Mockito", "CDI", "JUnit", "Hibernate", "Spring");

		Collections.sort(craftCoderGuides, (firstGuide, secondGuide) -> firstGuide.compareTo(secondGuide));

		Collections.sort(craftCoderGuides,
				(final String firstGuide, final String secondGuide) -> firstGuide.compareTo(secondGuide));

		assertThat(craftCoderGuides, contains("CDI", "Hibernate", "JUnit", "Mockito", "Spring"));
	}

	@Test()
	@DisplayName("Filtrar elementos mayores a 30")
	void testMapFilter() {
		List<Integer> list2 = stringList.stream().map(Integer::parseInt).filter(x -> x > 30)
				.collect(Collectors.toList());

		assertThat(list2, contains(60, 48));
	}

	@Test()
	@DisplayName("Calculo de un valor escalar")
	void testMax() {

		Optional<Integer> maximo = stringList.stream().map(Integer::parseInt).max(Comparator.comparing(x -> x));
		long count = stringList.stream().map(Integer::parseInt).filter(x -> x <= 20).count();

		assertEquals(new Integer(60), maximo.get());
		assertEquals(new Long(3), new Long(count));

	}

	@Test()
	@DisplayName("Filtrar elementos mayores de 30")
	void test() {
		List<Integer> list2 = stringList.stream().map(x -> Integer.parseInt(x)).filter(x -> x > 30)
				.collect(Collectors.toList());
	}

	@Test()
	@DisplayName("Filtrar elementos mayores de 30")
	void testMatch() {
		Boolean find = stringList.stream().map(x -> Integer.parseInt(x)).anyMatch(x -> x > 30);
	}

	@Nested
	@DisplayName("Comparator")
	class ComparatorTest {
		private Comparator<Employee> comp1;
		private Comparator<Employee> comp2;

		@BeforeEach
		void setUp() throws Exception {
			// Comparator
			comp1 = (first, second) -> first.getName().compareTo(second.getName());
			// Method reference
			comp2 = Comparator.comparing(Employee::getName).thenComparing(Comparator.comparing(Employee::getLastName));
		}

		@Test
		@DisplayName("Direct")
		void testComparatorsDirect() {
			Collections.sort(employeeList, comp1);

			List<String> mapName = employeeList.stream().map(Employee::getName).collect(Collectors.toList());
			assertThat(mapName, contains("A", "B", "C", "D"));

			Collections.sort(employeeList, comp2);

			mapName = employeeList.stream().map(Employee::getName).collect(Collectors.toList());
			assertThat(mapName, contains("A", "B", "C", "D"));
		}

		@Test
		@DisplayName("Reverse")
		void testComparatorsReverse() {
			Collections.sort(employeeList, comp2.reversed());

			List<String> mapName = employeeList.stream().map(Employee::getName).collect(Collectors.toList());
			assertThat(mapName, contains("D", "C", "B", "A"));

			Collections.sort(employeeList, comp2);
			employeeList.stream().forEach(Employee::incNumEmployee);

			assertThat(employeeList.stream().map(Employee::getNumEmployee).collect(Collectors.toList()),
					contains(2l, 3l, 4l, 5l));
		}
	}

	@Nested
	@DisplayName("Tests threads")
	class ThreadTest {
		@Test()
		@DisplayName("Runnable")
		void testRunnable() {
			Runnable runnable = () -> System.out.println("Awesome! Lambda Expression here!");
			runnable.run();
		}
	}

}
