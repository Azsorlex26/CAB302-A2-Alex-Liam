package assignment2.classes.truck;

import assignment2.classes.Item;
import assignment2.classes.Stock;
import assignment2.exceptions.StockException;

/**
 * This abstract class is the superclass for the two types of trucks
 * 
 * @author Alexander Rozsa
 * @author Liam Edwards
 */
public abstract class Truck {

	protected int maxCapacity;
	protected Stock cargo = new Stock();

	/**
	 * Adds items to the truck's cargo
	 * 
	 * @param item to be added
	 * @param quantity of item to be added
	 * @throws StockException if the number of items in the truck plus the quantity to be added exceeds the truck's maximum capacity
	 */
	public void add(Item item, int quantity) throws StockException {
		if ((cargo.totalQuantity() + quantity) <= maxCapacity) {
			cargo.add(item, quantity);
		} else {
			throw new StockException();
		}
	}

	/**
	 * Gets the cost
	 * 
	 * @return cost of the truck
	 */
	public abstract double getCost();

	/**
	 * Returns the amount of items that can be added to the truck
	 * 
	 * @return the maximum capacity minus the current number of items in the truck
	 */
	public int remainingCapacity() {
		return maxCapacity - cargo.totalQuantity();
	}

	/**
	 * Returns the cargo object
	 * 
	 * @return cargo object of the truck
	 */
	public Stock getCargo() {
		return cargo;
	}
}
