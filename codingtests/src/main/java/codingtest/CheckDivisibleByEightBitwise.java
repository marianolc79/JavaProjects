package codingtest;

//Check if a number is divisible by 8 using bitwise operators
public class CheckDivisibleByEightBitwise {

	public boolean checkDivisibleBy8(int num) {
		int bitmask = 0xFFF8;
		return (num & bitmask) == num;
	}
}
