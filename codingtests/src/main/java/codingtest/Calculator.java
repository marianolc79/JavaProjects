package codingtest;

public class Calculator {

	public static String sum(String... numbers) {
		float sum = 0.0f;
		for (String numStr : numbers) {
			sum += Float.parseFloat(numStr);
		}
		return new Float(sum).toString();
	}

	public static void main(String[] args) {
		System.out.println(sum("99.35", "1.10"));
		System.out.println(01 ^ 11);
	}

}
