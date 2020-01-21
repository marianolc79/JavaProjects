package codingtest;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Fibonacci {

	public static void main(String[] args) {
		List<BigInteger> list = getListIterative(100);
		System.out.println(list);
	}

	private static long fibonnacciRec(long n) {
		if (n == 1 || n == 2) {
			return 1;
		} else {
			return fibonnacciRec(n - 1) + fibonnacciRec(n - 2);
		}
	}

	private static List<BigInteger> getListIterative(int n) {
		BigInteger n1 = null;
		BigInteger n2 = null;
		List<BigInteger> list = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			if (i < 3) {
				if (i == 2) {
					n2 = n1;
				}
				n1 = new BigInteger(String.valueOf(i - 1));
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
}
