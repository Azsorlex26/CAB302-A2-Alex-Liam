package assignment2.classes;

public class Store {

	private String name;
	private Stock inventory;
	private double capital;
	
	/*
	 * Instantiates a new stock
	 */
	public Store(String name) {
		this.name = name;
		inventory = new Stock();
		capital = 100000.0; //Initial capital
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
