package codingtest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Fibonacci {
	private static final BigInteger[][] MATRIX_FIB = { //
			{ BigInteger.valueOf(1), BigInteger.valueOf(1) }, //
			{ BigInteger.valueOf(1), BigInteger.valueOf(0) }, //
	};
	private static final BigInteger[][] MATRIX_ID = { //
			{ BigInteger.valueOf(1), BigInteger.valueOf(0) }, //
			{ BigInteger.valueOf(0), BigInteger.valueOf(1) }, //
	};

	public static void main(String[] args) {
		List<BigInteger> list = fibonacciIter(100);

		for (BigInteger item : list) {
			System.out.println(item);
		}
		System.out.println(fibonacciMatrix(100));

		MultiplyMatrix.printMatrix(power(MATRIX_FIB, 100));
	}

	private static long fibonacciRec(long n) {
		if (n == 1 || n == 2) {
			return 1;
		} else {
			return fibonacciRec(n - 1) + fibonacciRec(n - 2);
		}
	}

	private static List<BigInteger> fibonacciIter(int n) {
		BigInteger n1 = null;
		BigInteger n2 = null;
		List<BigInteger> list = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			if (i < 2) {
				if (i == 1) {
					n2 = n1;
				}
				n1 = BigInteger.valueOf(i);
				list.add(n1);
			} else {
				BigInteger fib = n1.add(n2);
				list.add(fib);
				n2 = n1;
				n1 = fib;
			}
		}
		return list;
	}

	public static BigInteger[][] multiply2x2Matrix(BigInteger[][] a, BigInteger[][] b) {
		BigInteger[][] result = new BigInteger[2][2];
		result[0][0] = a[0][0].multiply(b[0][0]).add(a[0][1].multiply(b[1][0]));
		result[0][1] = a[0][0].multiply(b[0][1]).add(a[0][1].multiply(b[1][1]));
		result[1][0] = a[1][0].multiply(b[0][0]).add(a[1][1].multiply(b[1][0]));
		result[1][1] = a[1][0].multiply(b[0][1]).add(a[1][1].multiply(b[1][1]));
		return result;
	}

	public static BigInteger[][] power(BigInteger[][] a, int n) {
		if (n == 0)
			return MATRIX_ID;
		BigInteger[][] temp = power(a, n / 2);
		if (n % 2 == 0) {
			return multiply2x2Matrix(temp, temp);
		}
		return multiply2x2Matrix(multiply2x2Matrix(temp, temp), a);
	}

	private static BigInteger fibonacciMatrix(int n) {
		BigInteger[][] matrix = power(MATRIX_FIB, n - 1);
		return matrix[0][0];
	}

}
