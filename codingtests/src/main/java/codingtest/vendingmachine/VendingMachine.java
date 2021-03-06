package codingtest.vendingmachine;

import java.util.List;

public interface VendingMachine {

	/***
	 * You need to design a Vending Machine which Accepts coins of 1,5,10,25 Cents
	 * i.e. penny, nickel, dime, and quarter. Allow user to select products
	 * Coke(25), Pepsi(35), Soda(45) Allow user to take refund by canceling the
	 * request. Return selected product and remaining change if any Allow reset
	 * operation for vending machine supplier. *
	 * 
	 * @param args
	 */

	public void insertCoin(Coin coin);

	public int selectItemAndGetPrice(String itemName);

	public List<Coin> cancelOperation();

	public VendingMachineResult<Coin, List<Coin>> collectItemAndChange();

	public void reset();
}
