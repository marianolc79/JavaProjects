package codingtest;

public class TestPalindrome {

	public static boolean isPalindrome(String str) {
		int start = 0;
		int end = str.length() - 1;
		while (start < end) {
			if (str.charAt(start) != str.charAt(end)) {
				return false;
			}
			start++;
			end--;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(isPalindrome("AA"));
		System.out.println(isPalindrome("ABBA"));
		System.out.println(isPalindrome("ABCBA"));
		System.out.println(isPalindrome("ABCBATT"));
		System.out.println(isPalindrome("ABCBAT"));
	}

}
