package codingtest;

public class Kariakoo {

	/** Computes the position of the Kariakoo dancer. */
	public static int getPositionAt(final int n) {
		if (n % 6 == 0) {
			return 0;
		}
		if (n % 6 == 1) {
			return 1;
		}
		if (n % 6 == 2) {
			return -1;
		}
		if (n % 6 == 3) {
			return -4;
		}
		if (n % 6 == 4) {
			return -5;
		}
		if (n % 6 == 5) {
			return -3;
		}
		return 0;
	}

	public static void main(final String[] args) {
		System.out.println(Kariakoo.getPositionAt(3)); // -4
		System.out.println(Kariakoo.getPositionAt(100000)); // -5
		System.out.println(Kariakoo.getPositionAt(2147483647)); // 1
	}
}