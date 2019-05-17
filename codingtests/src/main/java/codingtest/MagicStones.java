package codingtest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MagicStones {

	/**
	 * You have a number N of magic stones each of which has a level with a positive
	 * integer value.
	 * 
	 * The stones are very heavy and cumbersome to carry around.
	 * 
	 * Luckily you can use your magic powers to combine two stones of level i to
	 * form a single stone of level i+1.
	 * 
	 * Use your power to minimise the number of stones that you have.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(magic(Arrays.asList(1, 1, 2, 3, 3, 2)));

	}

	public static int magic(List<Integer> stones) {
		Map<Integer, Integer> map = calculFrequencies(stones);

		while (true) {
			boolean modifie = false;
			for (Integer stone : map.keySet().toArray(new Integer[map.size()])) {
				Integer count = map.get(stone);
				if (count != 1) {
					if (count % 2 == 0) {
						map.remove(stone);
					} else {
						map.put(stone, 1);
					}
					if (map.containsKey(stone + 1)) {
						map.put(stone + 1, map.get(stone + 1) + count / 2);
					} else {
						map.put(stone + 1, count / 2);
					}
					modifie = true;
				}
			}
			if (!modifie) {
				break;
			}
		}

		int min = map.values().stream().mapToInt(i -> i).sum();
		return min;
	}

	private static Map<Integer, Integer> calculFrequencies(List<Integer> stones) {
		Map<Integer, Integer> map = new TreeMap<>();
		for (Integer stone : stones) {
			if (map.containsKey(stone)) {
				map.put(stone, map.get(stone) + 1);
			} else {
				map.put(stone, 1);
			}
		}
		return map;
	}

}
