package codingtest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsArrayContainingInOrder.arrayContaining;

import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;

public class TrueRectanglesTest {

	@Test
	public void testConcat1() {
		assertThat(ArrayUtils.toObject(TrueRectangles.concat(new int[] { 1, 2, 3 }, new int[] { 4, 5, 6 })),
				arrayContaining(1, 2, 3, 4, 5, 6));
	}

	@Test
	public void testConcat2() {
		assertThat(ArrayUtils.toObject(TrueRectangles.concat(new int[] { 1 }, new int[] { 4 })), arrayContaining(1, 4));
	}

	@Test
	public void testConcat3() {
		assertThat(ArrayUtils.toObject(TrueRectangles.concat(new int[] {}, new int[] { 4 })), arrayContaining(4));
	}

}
