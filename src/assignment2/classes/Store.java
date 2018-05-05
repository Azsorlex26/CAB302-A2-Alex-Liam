package assignment2.classes;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
	 * Returns the existing store
	 * 
	 * @return store
	 */
	public static Store getStore() {
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
	 * @return "$" + bd.doubleValue()
	 */
	public String getCapital() {
		BigDecimal bd = new BigDecimal(capital);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		return "$" + bd.doubleValue();
	}

	/**
	 * Increase or decrease the store's capital depending on if the input is
	 * positive or negative
	 * 
	 * @param amount
	 */
	public void addCapital(double amount) {
		capital += amount;
	}
}
