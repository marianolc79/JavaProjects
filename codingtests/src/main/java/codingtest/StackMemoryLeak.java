package codingtest;

import java.util.EmptyStackException;

public class StackMemoryLeak {

	private static long memStart = 0;

	private static void printMemory() {
		long used = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		System.out.println("Memory:" + (used - memStart) / 1024);
	}

	public static void main(String[] args) {
		memStart = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

		printMemory();
		Stack s = new Stack(1);
		printMemory();
		for (int i = 0; i < 10000; i++) {
			s.push(new Stuff());
		}
		printMemory();
		for (int i = 0; i < 10000; i++) {
			s.pop();
		}
		s = null;

		System.gc();
		printMemory();
	}
}

class Stuff {
	private int[] vals = new int[10000];
}

class Stack {
	private Object[] elements;
	private int size = 0;

	public Stack(int initialCapacity) {
		elements = new Object[initialCapacity];
	}

	public void push(Object e) {
		ensureCapacity();
		elements[size++] = e;
	}

	public Object pop() {
		if (size == 0)
			throw new EmptyStackException();
		Object result = elements[--size];
		elements[size] = null;
		return result;
	}

	private void ensureCapacity() {
		if (elements.length == size) {
			Object[] oldElements = elements;
			elements = new Object[2 * elements.length + 1];
			System.arraycopy(oldElements, 0, elements, 0, size);
		}
	}
}
