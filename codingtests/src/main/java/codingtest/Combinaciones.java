package codingtest;

import java.util.ArrayList;
import java.util.List;

/**
 * Calcular todas las posibles parejas de un conjunto (no importa el orden).
 * 
 * @author mariano
 *
 */
public class Combinaciones {

	public static String[] parejas(String[] str) {
		List<String> par = new ArrayList<>();
		for (int i = 0; i < str.length; i++) {
			for (int j = 0; j < str.length; j++) {
				if (i != j && !par.contains(str[i] + str[j]) && !par.contains(str[j] + str[i])) {
					par.add(str[i] + str[j]);
				}
			}
		}
		return par.toArray(new String[par.size()]);
	}

	public static void main(String[] args) {
		String[] parejas = parejas(new String[] { "A", "B", "C", "D", "E", "F" });
		for (String par : parejas) {
			System.out.println(par);
		}
		System.out.println(parejas.length);
	}

}
