package assignment2.classes;

import java.math.BigDecimal;
import java.math.RoundingMode;

import assignment2.exceptions.StockException;

/**
 * This class represents the store
 * 
 * @author Liam Edwards
 * @author Alexander Rozsa
 */
public class Store {

	private static Store store;
	private static Stock inventory;
	private static String name;
	private static double capital;

	/**
	 * Initializes the store
	 * 
	 * @param store_name
	 * @return store
	 */
	public static Store makeStore(String store_name) {
		if (store == null) {
			store = new Store();
			inventory = new Stock();
			name = store_name;
			capital = 100000;
		}
		return store;
	}

	/**
	 * Get the name of store
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get capital of store. Source:
	 * https://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-
	 * places
	 * 
	 * @return capital
	 */
	public double capital() {
		BigDecimal bd = new BigDecimal(capital);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	/**
	 * Returns the store's Stock object
	 * 
	 * @return inventory
	 */
	public Stock inventory() {
		return inventory;
	}

	/**
	 * Increase or decrease the store's capital depending on if the input is
	 * positive or negative
	 * 
	 * @param amount
	 */
	public void adjustCapital(double amount) {
		capital += amount;
	}

	/**
	 * Restocks the store's items if needed
	 * @throws StockException 
	 */
	public void restock() throws StockException {
		for (Item item : inventory) {
			if (inventory.reorder(item)) {
				inventory.add(item, item.reorderAmount());
			}
		}
	}

	/**
	 * Function needed to reset the store to null for the tests
	 */
	public static void nullifyStore() {
		store = null;
	}
}
