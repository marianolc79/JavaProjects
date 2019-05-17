package codingtest;

public class TrianguloPascal {

	public static void print(final int n) {
		long[] prec = null;
		long[] current = null;

		for (int i = 0; i < n; i++) {
			current = new long[i + 1];
			for (int j = 0; j <= i; j++) {
				if (j == i || j == 0) {
					current[j] = 1;
				} else {
					current[j] = prec[j - 1] + prec[j];
				}
			}
			printArray(current);
			prec = new long[current.length];
			System.arraycopy(current, 0, prec, 0, current.length);
		}
		int i = 0;
	}

	private static void printArray(final long[] array) {
		for (long item : array) {
			System.out.print(String.format("%4d", item));
		}
		System.out.println();
	}

	public static void main(final String[] args) {
		// TODO Auto-generated method stub
		print(20);
	}

}
