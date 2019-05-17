package codingtest;

public class IntegerPalindrome {

	public static void main(String[] args) {
		System.out.println(isPalindrome(123544321));
	}

	public static boolean isPalindrome(int n) {
		return n == inverse(n);
	}

	public static int inverse(int n) {
		int res = 1;
		int red = n;

		int c = 0;
		while (true) {
			int mod = red % 10;
			red = red / 10;
			res = res * (c > 0 ? 10 : 0) + mod;
			if (red == 0) {
				break;
			}
			c++;
		}
		return res;
	}

}
