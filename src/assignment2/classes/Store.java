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
	private static double capital;

	/**
	 * Initializes the store
	 * 
	 * @param store_name
	 * @return store
	 */
	public static void makeStore(String store_name) {
		if (inventory == null) {
			inventory = new Stock();
			name = store_name;
			capital = 100000;
		}
	}

	/**
	 * Get the name of store
	 * 
	 * @return name
	 */
	public static String getName() {
		return name;
	}

	/**
	 * Get capital of store.
	 * 
	 * @return capital
	 */
	public static double getCapital() {
		return capital;
	}

	/**
	 * Returns the store's Stock object
	 * 
	 * @return inventory
	 */
	public static Stock getInventory() {
		return inventory;
	}

	/**
	 * Update capital of store
	 * 
	 * @param amount to adjust (positive or negative)
	 */
	public static void adjustCapital(double amount) {
		capital += amount;
	}

	/**
	 * Function needed to reset the store to null for the tests
	 */
	public static void nullifyStore() {
		inventory = null;
	}
}