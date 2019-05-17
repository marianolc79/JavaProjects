package codingtest;

public class SumSubsequences {

	/**
	 * Given a number as string s, find the sum of all the elements present in all
	 * possible subsequences of s.
	 * 
	 * @param args
	 */
	private static int sumSubsequences(final String str) {
		int sum = 0;
		for (int i = 1; i <= str.length(); i++) {
			for (int j = 0; j <= str.length() - i; j++) {
				for (int k = j; k < j + i; k++) {
					System.out.print(str.charAt(k));
					sum += Integer.valueOf(String.valueOf(str.charAt(k)));
				}
				System.out.println();
			}
		}
		return sum;
	}

	/**
	 * Find all subsequences of a given string
	 * 
	 * @param str
	 */
	private static void findSubsequences(final String str) {
		for (int i = 1; i <= str.length(); i++) {
			findSubsequences(str, "", 0, i);
		}
	}

	private static void findSubsequences(final String str, final String substring, final int index, final int len) {

		for (int j = index; j <= str.length() - len; j++) {
			String val = String.valueOf(str.charAt(j));
			if (len == 1) {
				if ("".equals(substring)) {
					System.out.print(val);
				} else {
					System.out.print(substring + val);
				}
				System.out.println();
			} else {
				findSubsequences(str, substring + val, j + 1, len - 1);
			}
		}
	}

	public static void main(final String[] args) {
		// TODO Auto-generated method stub
		findSubsequences("1234598347");
	}

}
