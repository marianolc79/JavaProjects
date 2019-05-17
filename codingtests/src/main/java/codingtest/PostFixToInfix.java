package codingtest;

import java.util.Stack;
import java.util.regex.Pattern;

public class PostFixToInfix {

	private static String postFixToInfix(String postfixStr) {
		Stack<String> st = new Stack<String>();
		for (int i = 0; i < postfixStr.length(); i++) {
			String val = String.valueOf(postfixStr.charAt(i));
			if (isOperand(val)) {
				st.push(val);
			} else {
				String op2 = st.pop();
				String op1 = st.pop();
				st.push("(" + op1 + val + op2 + ")");
			}
		}
		return st.pop();
	}

	private static boolean isOperand(String opStr) {
		return Pattern.matches("[abc]", opStr);
	}

	public static void main(String[] args) {
		System.out.println(postFixToInfix("bb+ab*c+*"));
	}
}
