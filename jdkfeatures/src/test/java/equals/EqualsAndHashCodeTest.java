package equals;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import org.junit.Test;

public class EqualsAndHashCodeTest {

	class A {
		private String a;
		private String b;

		public String getA() {
			return a;
		}

		public String getB() {
			return b;
		}

		public A(String a, String b) {
			super();
			this.a = a;
			this.b = b;
		}

		@Override
		public int hashCode() {
			return Objects.hash(a, b);
		}

		@Override
		public boolean equals(Object obj) {
			return obj instanceof A && ((A) obj).getA().equals(((A) obj).getA())
					&& ((A) obj).getB().equals(((A) obj).getB());
		}

	}

	@Test
	public void equalsTest() {
		A a = new A("1", "2");
		A b = new A("1", "2");

		assertEquals(a, b);
		assertEquals(a.hashCode(), b.hashCode());
		assertFalse(a == b);

		List<A> list = new ArrayList<A>(4);
		list.add(a);
		list.add(a);
		list.add(b);
		assertEquals(3, list.size());

		HashSet<A> set = new HashSet<A>(4);
		set.add(a);
		set.add(a);
		set.add(b);
		assertEquals(1, set.size());

	}
}
