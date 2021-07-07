package jdkfeatures;

import java.util.List;

public class JdkFeatures {

	public static void main(String[] args) {
		// JDK 9
		List<Integer> integerList = List.of(1, 2);

		List<Integer> copyList = List.copyOf(integerList);
		copyList.add(4);

		var a = "A";
	}

}
