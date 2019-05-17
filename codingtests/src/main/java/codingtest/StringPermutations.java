package codingtest;

public class StringPermutations {

	public static void perm(String str, String prefix) {
		if (str.length() == 0) {
			System.out.println(prefix);
		} else {
			for (int i = 0; i < str.length(); i++) {
				perm(str.substring(0, i) + str.substring(i + 1), prefix + String.valueOf(str.charAt(i)));
			}
		}
	}

	public static void main(String[] args) {
		perm("ABCD3428", "");
	}
}
