package codingtest.vendingmachine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachineImpl implements VendingMachine {

	private Map<Item, Integer> stock = new HashMap<Item, Integer>();

	public VendingMachineImpl() {

	}

	public void init(Item item, int quantity) {
		stock.put(item, quantity);
	}

	@Override
	public void insertCoin(Coin coin) {

	}

	@Override
	public int selectItemAndGetPrice(String itemName) {
		return 0;
	}

	@Override
	public List<Coin> cancelOperation() {
		return null;
	}

	@Override
	public VendingMachineResult<Coin, List<Coin>> collectItemAndChange() {
		return null;
	}

	@Override
	public void reset() {

	}

}
