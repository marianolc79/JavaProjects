package codingtest;

import java.util.HashMap;

/*
 * Given a string, count how many maximum-length palindromes are present.(It need not be a substring)
 */
public class MaximumPalindromes {

	public int fact(int n) {
		int fact = 1;
		for (int i = 1; i <= n; i++) {
			fact *= i;
		}
		return fact;
	}

	public int maxPalindromes(String str) {
		HashMap<Character, Integer> occurences = new HashMap<Character, Integer>();

		for (int i = 0; i < str.length(); i++) {
			if (occurences.containsKey(str.charAt(i))) {
				int value = occurences.get(str.charAt(i)) + 1;
				occurences.put(str.charAt(i), value);
			} else {
				occurences.put(str.charAt(i), 1);
			}
		}

		int num = 0;
		int numSingles = 0;
		int den = 1;

		// Num permutaciones n! / (n1! n2! n3!) n1, n2, n3 son elementos que se
		// repiten
		int answer;
		for (Character c : occurences.keySet()) {
			int freq = occurences.get(c);

			int freq2;
			if (freq % 2 == 0) {
				freq2 = freq / 2;
			} else {
				freq2 = (freq - 1) / 2;
				numSingles++;
			}
			num += freq2;
			den *= fact(freq2);
		}

		if (num != 0) {
			num = fact(num);
		}

		answer = num / den;

		// Obtenemos las combinaciones con cada uno de los elementos centrales
		if (numSingles != 0) {
			answer *= numSingles;
		}

		return answer;
	}

	// public static void main(String[] args) {
	// System.out.println(maxPalindromes("ababab8988665"));
	// }
}
