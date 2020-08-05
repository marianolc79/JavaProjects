package generics;

import java.util.List;

public class Algorithm {
	public static <T extends Comparable<? super T>> T findMaximum(List<? extends T> list) {
		T max = list.get(0);
		for (T item : list) {
			if (item.compareTo(max) > 0) {
				max = item;
			}
		}
		return max;
	}

	public static <T extends Number> T findMaximumNum(List<T> list) {
		T max = list.get(0);
		for (T item : list) {
			if (item.doubleValue() > max.doubleValue()) {
				max = item;
			}
		}
		return max;
	}
}
