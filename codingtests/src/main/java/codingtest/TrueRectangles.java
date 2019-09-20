package codingtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TrueRectangles {

	/***
	 * A "True Rectangle" is a rectangle with two different dimensions and four
	 * equal angles.
	 * 
	 * We want to decompose a given true rectangle into the minimum number of
	 * squares, Then aggregate these generated squares together to form all the
	 * possible true rectangles.
	 * 
	 * As shown in this figure, we want to decompose the (13*5) true rectangle into
	 * the minimum number of squares which are [5, 5, 3, 2, 1, 1] to return all the
	 * possible true rectangles from aggregating these squares together :
	 * 
	 * rectIntoRects(13, 5) should return the rectangles: ["(10*5)", "(8*5)",
	 * "(2*1)", "(3*2)", "(5*3)", "(13*5)"] //or any other order
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Integer[] result = rectIntoSquares2(23, 3);

		System.out.println(Arrays.toString(rectIntoSquares(13, 5)));
		System.out.println(Arrays.toString(rectIntoSquares(28, 11)));
		System.out.println(Arrays.toString(rectIntoSquares(13, 21)));
		System.out.println(Arrays.toString(rectIntoSquares(12, 19)));
		int i = 0;
	}

	static class Rect {
		private int x;
		private int y;

		public Rect(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getx() {
			return x;
		}

		public int gety() {
			return y;
		}

		@Override
		public String toString() {
			return "(" + x + "x" + y + ")";
		}

	}

	private static int[] multiply(int value, int times) {
		int[] result = new int[times];
		for (int i = 0; i < times; i++) {
			result[i] = value;
		}
		return result;
	}

	public static int[] concat(int[] arr1, int[] arr2) {
		int[] result = new int[arr1.length + arr2.length];
		System.arraycopy(arr1, 0, result, 0, arr1.length);
		System.arraycopy(arr2, 0, result, arr1.length, arr2.length);
		return result;
	}

	public static int[][] rectIntoRects(int x, int y) {
		int[] squares = rectIntoSquares(x, y);
		Rect[] rects = rectIntoRectsPriv(squares);
		return null;
	}

	private static Rect[] rectIntoRectsPriv(int[] squares) {

		List<Rect> list = new ArrayList<Rect>();
		for (int i = 1; i < squares.length; i++) {
			if (squares[i] == squares[i - 1]) {

				new Rect(squares[i] * 2, squares[i]);
			} else {
				new Rect(squares[i - 2], squares[i] * 2);
			}
//			if (squares[i] == squares[i - 1]) {
//				Rect rect = new Rect(squares[i], squares[i] * 2);
//
//				for (Rect r : new ArrayList<Rect>(list)) {
//					if (r.gety() == squares[i]) {
//						Rect rect1 = new Rect(r.getx() + squares[i], r.gety());
//						list.add(rect1);
//					}
//					if (r.getx() == squares[i]) {
//						Rect rect1 = new Rect(r.getx(), r.gety() + squares[i]);
//						list.add(rect1);
//					}
//				}
//				list.add(rect);
//			} else if (i >= 2 && squares[i] + squares[i - 1] == squares[i - 2]) {
//				Rect rect1 = new Rect(squares[i] + squares[i - 1], squares[i - 2]);
//				list.add(rect1);
//			}
		}
		return list.toArray(new Rect[] {});
	}

	public static int[] rectIntoSquares(int x, int y) {
		int maxDimension = Math.max(x, y);
		int minDimension = Math.min(x, y);

		int num = (maxDimension / minDimension);

		int rectResult = (maxDimension % minDimension);

		if (rectResult == 0) {
			return multiply(minDimension, num);
		} else {
			return concat(multiply(minDimension, num), rectIntoSquares(rectResult, minDimension));
		}
	}

	public static Integer[] rectIntoSquares2(int x, int y) {
		if (x == y) {
			return null;
		}
		List<Integer> list = new ArrayList<>();
		while (y != x) {
			if (y > x) {
				y -= x;
				list.add(x);
			} else if (x > y) {
				x -= y;
				list.add(y);
			}
		}
		list.add(y);
		return list.stream().map(Integer::intValue).collect(Collectors.toList()).toArray(new Integer[] {});
	}

}
