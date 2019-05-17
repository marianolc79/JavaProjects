package codingtest.arrays;

import java.util.Arrays;

public class ReverseArrayInPlace {

	public static void main(String[] args) {
		int[] array = new int[] { 2, 3, 4, 5, 6 };
		rerverseArray(array);
		System.out.println(Arrays.toString(array));
	}

	public static void rerverseArray(int[] array) {
		if (array.length <= 1) {
			return;
		}
		for (int i = 0; i < (array.length / 2); i++) {
			int temp = array[i];
			array[i] = array[(array.length - 1) - i];
			array[(array.length - 1) - i] = temp;
		}
	}

}
