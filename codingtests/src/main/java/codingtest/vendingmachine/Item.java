package codingtest.vendingmachine;

public enum Item {
	COKE(25), PEPSI(35), SODA(45);

	private double price;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	private Item(double price) {
		this.price = price;
	}

}
