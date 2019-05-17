package perf;

import java.util.Random;

public class RandomData {
	private static Random rnd = new Random(System.currentTimeMillis());
	
	public static String generateRandomString(int size) {
		char[] charArray = new char[size];
		for(int i = 0;i<size;i++) {
			 charArray[i] = (char) (48 + rnd.nextInt(47));
		}
		return new String(charArray);
	}
	
	
	public static int generateRandomInt(int min, int max) {
		return min + rnd.nextInt(max)+1;
	}
}
