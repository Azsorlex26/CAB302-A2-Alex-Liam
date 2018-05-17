package assignment2.classes;

import java.math.BigDecimal;
import java.math.RoundingMode;

import assignment2.exceptions.StockException;

/**
 * This class represents the store
 * 
 * @author Alexander Rozsa
 * @author Liam Edwards
 */
public class Store {

	public static Store store;
	public static Stock inventory;
	public static String name;
	public static double capital;

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
	 * Formats the double to two decimal places. Source:
	 * https://stackoverflow.com/questions/2808535/round-a-double-to-2-decimal-
	 * places
	 * 
	 * @param amount
	 * @return formatted double
	 */
	private double formatDouble(double amount) {
		BigDecimal bd = new BigDecimal(amount);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		return bd.doubleValue();
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
	 * Get capital of store.
	 * 
	 * @return capital
	 */
	public double getCapital() {
		return formatDouble(capital);
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
		capital += formatDouble(amount);
	}

	/**
	 * Restocks the store's items if needed
	 * 
	 * @throws StockException
	 */
	public void restock() throws StockException {
		for (Item item : inventory) {
			if (inventory.reorder(item)) {
				inventory.add(item, item.getReorderAmount());
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
