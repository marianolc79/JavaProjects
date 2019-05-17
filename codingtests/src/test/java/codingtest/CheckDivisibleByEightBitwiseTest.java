package codingtest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class CheckDivisibleByEightBitwiseTest {

	private CheckDivisibleByEightBitwise c = new CheckDivisibleByEightBitwise();

	@DisplayName("Caso pasante")
	@Test
	void testCheckDivisibleBy8() {
		Assert.assertTrue(c.checkDivisibleBy8(8));
	}

	@DisplayName("Caso no pasante")
	@Test
	void testCheckDivisibleBy8False() {
		Assert.assertFalse(c.checkDivisibleBy8(10));
	}

	private static Stream<Arguments> provideParams() {
		List<Arguments> list = new ArrayList<Arguments>();
		for (int i = 0; i <= 100; i++) {
			list.add(Arguments.of(i, i % 8 == 0));
		}
		return list.stream();
	}

	@ParameterizedTest(name = "\"{0}\" should be {1}")
	@MethodSource("provideParams")
	void testCheckDivisibleBy8Params(int num, boolean expectedValue) {
		Assert.assertEquals(expectedValue, c.checkDivisibleBy8(num));
	}
}
