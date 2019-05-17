package codingtest;

public class GreatestCommonDivisor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(gcd(54, 24));
	}

	public static int gcd(int a, int b) {
		for (int i = Math.min(a, b); i > 1; i--) {
			if (a % i == 0 && b % i == 0) {
				return i;
			}
		}
		return 1;
	}
}
