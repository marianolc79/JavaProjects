package jeerest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

@DisplayName("Test de lambda expressions")
public class LambdaExpressionTest {

	@Test
	public void shouldRunUsingAnonymousClass() throws Exception {
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				System.out.println("Yes, anonymous object here :(");
			}
		};

		runnable.run();
	}

	@Test
	public void shouldRunRunnableObjectWithoutAnonymousClass() throws Exception {
		Runnable runnable = () -> System.out.println("Awesome! Lambda Expression here!");

		runnable.run();
	}

	@Test
	public void shouldOrderTheListOfNamesByUsingAnonymousClass() throws Exception {
		List<String> craftCoderGuides = Arrays.asList("Mockito", "CDI", "JUnit", "Hibernate", "Spring");

		Collections.sort(craftCoderGuides, new Comparator<String>() {

			@Override
			public int compare(final String firstGuide, final String secondGuide) {
				return firstGuide.compareTo(secondGuide);
			}
		});

		assertThat(craftCoderGuides, contains("CDI", "Hibernate", "JUnit", "Mockito", "Spring"));
	}

	@Test
	public void shouldOrderTheListOfNamesByLambdaExpression() throws Exception {
		List<String> craftCoderGuides = Arrays.asList("Mockito", "CDI", "JUnit", "Hibernate", "Spring");

		Collections.sort(craftCoderGuides, (final String firstGuide, final String secondGuide) -> {
			return firstGuide.compareTo(secondGuide);
		});

		assertThat(craftCoderGuides, contains("CDI", "Hibernate", "JUnit", "Mockito", "Spring"));
	}

	@Test
	public void shouldOrderTheListOfNamesByLambdaExpressionWithoutBracesAndReturnKeyword() throws Exception {
		List<String> craftCoderGuides = Arrays.asList("Mockito", "CDI", "JUnit", "Hibernate", "Spring");

		Collections.sort(craftCoderGuides,
				(final String firstGuide, final String secondGuide) -> firstGuide.compareTo(secondGuide));

		assertThat(craftCoderGuides, contains("CDI", "Hibernate", "JUnit", "Mockito", "Spring"));
	}

	@Test
	public void shouldOrderTheListOfNamesByLambdaExpressionWithoutParameterTypes() throws Exception {
		List<String> craftCoderGuides = Arrays.asList("Mockito", "CDI", "JUnit", "Hibernate", "Spring");

		Collections.sort(craftCoderGuides, (firstGuide, secondGuide) -> firstGuide.compareTo(secondGuide));

		assertThat(craftCoderGuides, contains("CDI", "Hibernate", "JUnit", "Mockito", "Spring"));
	}

}
