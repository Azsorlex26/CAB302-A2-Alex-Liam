package assignment2.classes;

/**
 * This class represents the store
 * 
 * @author Alexander Rozsa
 * @author Liam Edwards
 */
public class Store {

	private static Stock inventory;
	private static String name;
	private static Double capital;

	/**
	 * Initializes the store
	 * 
	 * @param store_name
	 */
	public static void makeStore(String store_name) {
		if (inventory == null) {
			inventory = new Stock();
			name = store_name;
			capital = 100000.0;
		}
	}

	/**
	 * Get the name of store
	 * 
	 * @return name of the store
	 */
	public static String getName() {
		return name;
	}

	/**
	 * Get capital of store.
	 * 
	 * @return capital of the store
	 */
	public static Double getCapital() {
		return capital;
	}

	/**
	 * Returns the store's Stock object
	 * 
	 * @return inventory object
	 */
	public static Stock getInventory() {
		return inventory;
	}

	/**
	 * Update capital of store
	 * 
	 * @param amount to adjust capital by (positive or negative)
	 */
	public static void adjustCapital(double amount) {
		capital += amount;
	}

	/**
	 * Resets the store's values to null. Required for the tests
	 */
	public static void nullifyStore() {
		inventory = null;
		name = null;
		capital = null;
	}
}