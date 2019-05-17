package codingtest.vendingmachine;

public class VendingMachineResult<A, B> {
	private A first;
	private B second;

	public A getFirst() {
		return first;
	}

	public void setFirst(A a) {
		this.first = a;
	}

	public B getSecond() {
		return second;
	}

	public void setSecond(B b) {
		this.second = b;
	}

	public VendingMachineResult(A a, B b) {
		super();
		this.first = a;
		this.second = b;
	}
}
