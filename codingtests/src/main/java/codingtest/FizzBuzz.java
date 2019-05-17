package codingtest;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FizzBuzz {
	public static String fizzBuzz(int number, Map<Integer, String> map) {
		StringBuilder sb = new StringBuilder();

		List<Integer> listKeys = Arrays.asList(map.keySet().toArray(new Integer[map.keySet().size()]));
		Collections.sort(listKeys);
		for (Integer key : listKeys) {
			if (number % key == 0) {
				sb.append(map.get(key));
			}
		}
		if (sb.length() == 0) {
			return String.valueOf(number);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		Map<Integer, String> map = new HashMap<>();
		map.put(new Integer(8), "yu");
		map.put(new Integer(3), "1u2");
		map.put(new Integer(16), "Zzz");
		map.put(new Integer(2), "334");
		map.put(new Integer(7), "198");

		System.out.println(fizzBuzz(13, map));
	}

}
