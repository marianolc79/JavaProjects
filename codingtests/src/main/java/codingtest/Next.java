package codingtest;

import java.util.Arrays;

public class Next {

	private static boolean contains(int[] array, int a) {
		for (int item : array) {
			if (item == a) {
				return true;
			}
		}
		return false;
	}

	public static int next(int n) {
		String nStr = String.valueOf(n);

		char[] charArray = "0123456789".replaceAll("[" + nStr + "]", "").toCharArray();
		int[] chiffresValides = new int[charArray.length];
		for (int i = 0; i < charArray.length; i++) {
			chiffresValides[i] = Character.getNumericValue(charArray[i]);
		}
		Arrays.sort(chiffresValides);

		StringBuilder sb = new StringBuilder();

		int pchiffre = Character.getNumericValue(nStr.charAt(0));
		for (int i = 0; i < chiffresValides.length; i++) {
			if (chiffresValides[i] > pchiffre) {
				sb.append(chiffresValides[i]);
				break;
			}
		}
		if (sb.length() == 0) {
			if (contains(chiffresValides, 1) && contains(chiffresValides, 0)) {
				sb.append("10");
			} else {
				return -1;
			}
		}
		for (int i = 1; i < nStr.length(); i++) {
			sb.append(chiffresValides[0]);
		}
		return Integer.parseInt(sb.toString());
	}

	public static void main(String[] args) {
		System.out.println(next(654321));
		System.out.println(next(800023));
		System.out.println(next(1234567890));
		System.out.println(next(922));
	}

}
