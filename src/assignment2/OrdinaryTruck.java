package assignment2;

public class OrdinaryTruck extends Truck {

	/*
	 * Constructs the OrdinaryTruck object
	 */
	public OrdinaryTruck() {
		maxCapacity = 1000;
	}
	
	/*
	 * Gets the cost
	 * @return cost
	 */
	public double getCost() {
		return (750 + (0.25 * cargo.size()));
	}
	
}