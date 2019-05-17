package codingtest;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class MissingNumber {
	/**
	 * 1. How to find the missing number in integer array of 1 to 100? (solution)
	 * This is one of the most simple array problems you will see, mostly asked in a
	 * telephonic round of Interview. You have given an integer array which contains
	 * numbers from 1 to 100 but one number is missing, you need to write a Java
	 * program to find that missing number in an array. You cannot use any open
	 * source library or Java API method which solves this problem. One trick to
	 * solve this problem is to calculate sum of all numbers in the array and
	 * compare with expected sum, the difference would be the missing number.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[] array = initArray();
		int sum = 0;
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}
		// Expected sum = 101 * 50 = 5050
		int missing = (5050 - sum);
		System.out.println("Missing item=>" + missing);
	}

	static void shuffleArray(int[] ar) {
		Random rnd = ThreadLocalRandom.current();
		for (int i = ar.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			// Simple swap
			int a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}

	private static int[] initArray() {
		int[] array = new int[100];
		for (int i = 0; i < 100; i++) {
			array[i] = i + 1;
		}
		shuffleArray(array);
		int[] copy = new int[99];
		System.arraycopy(array, 0, copy, 0, 99);
		System.out.println("Expected missing item=>" + array[99]);
		return copy;
	}
}
