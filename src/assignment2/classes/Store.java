package assignment2.classes;

/**
 * This class represents a store
 * @author Liam Edwards
 * @author Alexander Rozsa
 */
public class Store {

	private static String name;
	private Stock inventory;
	private double capital;
	private static Store store;
	
	/*
	 * Instantiates a new store
	 */
	private Store() {
		inventory = new Stock();
		capital = 100000.0; //Initial capital
	}
	
	/*
	 * Initialises the store
	 * 
	 * @param name for the store
	 */
	public static Store getStore(String store_name) {
		if(store == null){
            store = new Store();
            name = store_name;
        }
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
