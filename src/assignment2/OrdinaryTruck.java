package assignment2;

public class OrdinaryTruck extends Truck {

	public OrdinaryTruck() {
		maxCapacity = 1000;
	}
	
	public double getCost() {
		return (750 + (0.25 * cargo.size()));
	}
	
}