package codingtest;

import java.util.HashMap;

public class StringPermutationsPalindrome {

	/**
	 * Given a string, convert the string to palindrome without any modifications
	 * like adding a character, removing a character, replacing a character etc.
	 * 
	 * Input : "mdaam" Output : "madam" or "amdma"
	 * 
	 * Input : "abb" Output : "bab"
	 * 
	 * Input : "geeksforgeeks" Output : "No Palindrome"
	 */

	private static String extractPalindrome(String str) {

		HashMap<Character, Integer> occurences = new HashMap<Character, Integer>();

		char[] ret = new char[str.length()];

		for (int i = 0; i < str.length(); i++) {
			if (occurences.containsKey(str.charAt(i))) {
				int value = occurences.get(str.charAt(i)) + 1;
				occurences.put(str.charAt(i), value);
			} else {
				occurences.put(str.charAt(i), 1);
			}
		}

		int i = 0;
		boolean oddFound = false;
		for (Character c : occurences.keySet()) {
			int num = occurences.get(c);
			if (num % 2 != 0) {
				if (oddFound) {
					return null;
				}
				oddFound = true;
			}
			for (int j = i; j < (i + (num / 2)); j++) {
				ret[j] = c;
				ret[str.length() - j - 1] = c;
			}
			if (num % 2 != 0) {
				ret[str.length() / 2] = c;
			}
			i += (num / 2);
		}

		return new String(ret);
	}

	public static void main(String[] args) {
		System.out.println(extractPalindrome("AABAA7B7B77"));
		System.out.println(extractPalindrome("AAB"));
		System.out.println(extractPalindrome("CBBCCAC1A"));
	}

}
