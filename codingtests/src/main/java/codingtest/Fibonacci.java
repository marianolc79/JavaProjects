package codingtest;

public class Fibonacci {

	public static void main(String[] args) {
		printIterative(100);
	}

	private static long fibonnacciRec(long n) {
		if (n == 1 || n == 2) {
			return 1;
		} else {
			return fibonnacciRec(n - 1) + fibonnacciRec(n - 2);
		}
	}

	private static void printIterative(long n) {
		long n1 = 0;
		long n2 = 0;
		for (int i = 0; i < n; i++) {
			long fib = (n1 + n2) == 0 ? 1 : (n1 + n2);
			System.out.println(fib);
			if (n2 != 0) {
				n1 = n2;
			}
			n2 = fib;
		}
	}
}
