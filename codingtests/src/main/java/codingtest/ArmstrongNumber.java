package codingtest;

public class ArmstrongNumber {

	/*
	 * A number is called an Armstrong number if it is equal to the cube of its each
	 * digit. for example, 153 is an Armstrong number because 153= 1+ 125+27 which
	 * is equal to 1^3+5^3+3^3. You need to write a program to check if given number
	 * is Armstrong number or not.
	 */
	public static void main(String[] args) {
		System.out.println(isArmstrong(153));
	}

	public static boolean isArmstrong(int n) {
		int red = n;
		double res = 0;
		while (true) {
			int mod = red % 10;
			red = red / 10;
			res = res + Math.pow(mod, 3);
			if (red == 0) {
				break;
			}
		}
		return res == n;
	}

}
