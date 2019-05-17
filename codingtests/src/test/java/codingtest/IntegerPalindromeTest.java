package codingtest;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class IntegerPalindromeTest {

	@Test
	void testIsPalindrome() {
		Assert.assertFalse(IntegerPalindrome.isPalindrome(123));
		Assert.assertTrue(IntegerPalindrome.isPalindrome(12321));
		Assert.assertFalse(IntegerPalindrome.isPalindrome(11123));
		Assert.assertTrue(IntegerPalindrome.isPalindrome(11211));
	}

}
