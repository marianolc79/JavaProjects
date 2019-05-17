package codingtest;

public class FindNumber {

	public static boolean exists(int[] ints, int k) {
		return exists(ints, k, 0, ints.length - 1);
	}

	private static boolean exists(int[] ints, int k, int start, int end) {
		if (ints[start] == k) {
			return true;
		} else if (start == end) {
			return false;
		} else {
			int index = start + (end - start) / 2;
			return exists(ints, k, start, index) || exists(ints, k, index + 1, end);
		}
	}

	public static void main(String[] args) {
		System.out.println("Test => " + exists(new int[] { 13, 2, 3, 4, 5, 88 }, 55));
	}
}
