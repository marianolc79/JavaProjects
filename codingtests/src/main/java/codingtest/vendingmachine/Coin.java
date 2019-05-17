package codingtest.vendingmachine;

public enum Coin {
	C1(1), C5(5), C10(10), C25(25);

	private int price;

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	private Coin(int price) {
		this.price = price;
	}
}
