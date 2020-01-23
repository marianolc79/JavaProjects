package codingtest;

import java.math.BigInteger;

public class MultiplyMatrix {
	private static final BigInteger[][] MATRIX_1 = { //
			{ BigInteger.valueOf(3), BigInteger.valueOf(2), BigInteger.valueOf(1) }, //
			{ BigInteger.valueOf(1), BigInteger.valueOf(1), BigInteger.valueOf(3) }, //
			{ BigInteger.valueOf(0), BigInteger.valueOf(2), BigInteger.valueOf(1) }, //
	};
	private static final BigInteger[][] MATRIX_2 = { //
			{ BigInteger.valueOf(2), BigInteger.valueOf(1) }, //
			{ BigInteger.valueOf(1), BigInteger.valueOf(0) }, //
			{ BigInteger.valueOf(3), BigInteger.valueOf(2) }, //
	};

	public static void printMatrix(BigInteger[][] a) {

		for (int row = 0; row < a.length; row++) {
			System.out.print("[");
			for (int col = 0; col < a[row].length; col++) {
				if (col > 0) {
					System.out.print(" ");
				}
				System.out.print(a[row][col]);
			}
			System.out.println("]");
		}
	}

	private static BigInteger multiplyCell(BigInteger[][] a, BigInteger[][] b, int row, int col) {
		BigInteger result = BigInteger.ZERO;
		for (int c = 0; c < a[row].length; c++) {
			result = result.add(a[row][c].multiply(b[c][col]));
		}
		return result;

	}

	private static BigInteger[][] multiply(BigInteger[][] a, BigInteger[][] b) {
		BigInteger[][] result = new BigInteger[a.length][];
		for (int row = 0; row < a.length; row++) {
			result[row] = new BigInteger[b[0].length];
			for (int col = 0; col < b[0].length; col++) {
				result[row][col] = multiplyCell(a, b, row, col);
			}
		}
		return result;
	}

	public static void main(String[] args) {

		printMatrix(multiply(MATRIX_1, MATRIX_2));
	}
}
