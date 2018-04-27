package assignment2;

public class Store {

	private String name;
	private Stock inventory;
	private double capital;
	
	public Store(String name) {
		this.name = name;
		inventory = new Stock();
		capital = 100000.0; //Initial capital
	}

	public String getName() {
		return name;
	}
	
	public double getCapital() {
		return capital;
	}
}
