package codingtest;

import java.util.Stack;

public class StringParser {

	/*
	 * Cet exercice consiste �identifier une chaine de caract�res compos�e de
	 * parenth�ses () et de crochets []. Une chaine de ce type est consid�r�e comme
	 * correcte : si c'est une chaine vide ou null si la chaine A est correcte, (A)
	 * et [A] sont correctes si les chaines A et B sont correctes, la concat�nation
	 * AB est �galement correcte Donn�es : La chaine contient au plus 10 000
	 * caract�res. Exemples : [()] est correcte, (()[]) est correcte, ([)] n'est pas
	 * correcte, (( n'est pas correcte. Answer concat Result Fail
	 */

	public static boolean check(String str) {
		if ("".equals(str) || str == null) {
			return true;
		} else if (str.charAt(0) == '(' && str.charAt(str.length() - 1) == ')') {
			return check(str.substring(1, str.length() - 1));
		} else if (str.charAt(0) == '[' && str.charAt(str.length() - 1) == ']') {
			return check(str.substring(1, str.length() - 1));
		} else if (str.length() == 2) {
			return false;
		} else {
			return check(str.substring(0, 2)) && check(str.substring(2));
		}
	}

	public static boolean check2(String str) {
		Stack<Character> stack = new Stack<Character>();

		for (int i = 0; i < str.length(); i++) {
			Character item = str.charAt(i);
			if (item == '[') {
				stack.push(item);
			}
			if (item == ']' && stack.peek() == '[') {
				stack.pop();
			}
			if (item == '(') {
				stack.push(item);
			}
			if (item == ')' && stack.peek() == '(') {
				stack.pop();
			}
		}
		return stack.size() == 0;
	}

	public static void main(String[] args) {
		System.out.println(check2("[()]"));
		System.out.println(check2("(()[])"));
		System.out.println(check2("([)]"));
		System.out.println(check2("((()))"));

	}

}
