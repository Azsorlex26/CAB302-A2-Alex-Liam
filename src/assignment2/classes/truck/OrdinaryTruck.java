package assignment2.classes.truck;

import assignment2.classes.Item;
import assignment2.exceptions.StockException;

/**
 * This class represents an ordinary truck
 * 
 * @author Alexander Rozsa
 * @author Liam Edwards
 */

public class OrdinaryTruck extends Truck {

	/**
	 * Constructs the OrdinaryTruck object
	 */
	public OrdinaryTruck() {
		maxCapacity = 1000;
	}

	/**
	 * Adds items to the truck's cargo
	 * 
	 * @param item to be added
	 * @param quantity of item to be added
	 * @throws StockException if the item has a temperature threshold
	 */
	@Override
	public void add(Item item, int quantity) throws StockException {
		if (item.getTempThreshold() == null) {
			super.add(item, quantity);
		} else {
			throw new StockException();
		}
	}

	/**
	 * Gets the cost
	 * 
	 * @return cost of the truck
	 */
	@Override
	public double getCost() {
		return (750 + (0.25 * cargo.totalQuantity()));
	}
}
