package assignment2.classes.truck;
/**
 * This class represents an ordinary truck
 * @author Liam Edwards
 * @author Alexander Rozsa
 */

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
		return (750 + (0.25 * cargo.totalQuantity()));
	}
	
}