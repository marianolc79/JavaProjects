package codingtest;

public class PrimeNumber {

	public static void main(String[] args) {
		printPrimes(100000);
	}

	public static void printPrimes(int n) {
		for (int i = 2; i <= n; i++) {
			if (isPrime(i)) {
				System.out.println(i);
			}
		}
	}

	public static boolean isPrime(int n) {
		int sqrt = (int) Math.sqrt(n);
		for (int i = sqrt; i > 1; i--) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}
}
