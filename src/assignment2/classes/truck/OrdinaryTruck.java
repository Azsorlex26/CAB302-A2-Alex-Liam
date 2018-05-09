package assignment2.classes.truck;

import assignment2.classes.Item;
import assignment2.exceptions.StockException;

/**
 * This class represents an ordinary truck
 * 
 * @author Liam Edwards
 * @author Alexander Rozsa
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
	 * @param item
	 * @param quantity
	 * @throws StockException
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
	 * @return cost
	 */
	public double cost() {
		return (750 + (0.25 * cargo.totalQuantity()));
	}
}