package assignment2.classes;

/**
 * This class represents a store
 * @author Liam Edwards
 * @author Alexander Rozsa
 */
public class Store {

	private static String name;
	private static Stock inventory;
	private static double capital;
	private static Store store;

	/*
	 * Initialises the store
	 * @param name for the store
	 * @return store
	 */
	public static Store makeStore(String store_name) {
		if (store == null) {
			store = new Store();
			name = store_name;
			inventory = new Stock();
			capital = 100000.0;
		}
		return store;
	}

	/*
	 * Returns the existing store
	 * @return store
	 */
	public static Store getStore() {
		return store;
	}

	/*
	 * Get the name of store
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/*
	 * Get capital of store
	 * @return capital
	 */
	public double getCapital() {
		return capital;
	}
}
