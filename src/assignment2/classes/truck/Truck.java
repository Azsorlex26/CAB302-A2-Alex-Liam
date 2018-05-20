package assignment2.classes.truck;

import assignment2.classes.Item;
import assignment2.classes.Stock;
import assignment2.exceptions.StockException;

/**
 * This abstract class is the superclass for the two types of trucks
 * 
 * @author Liam Edwards
 * @author Alexander Rozsa
 */
public abstract class Truck {

	protected int maxCapacity;
	protected Stock cargo = new Stock();

	/**
	 * Adds items to the truck's cargo
	 * 
	 * @param item
	 * @param quantity
	 * @throws StockException
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
	 */
	abstract double getCost();
	
	/**
	 * Gets the maximum capacity
	 * 
	 * @return maxCapacity
	 */
	public int maxCapacity() {
		return maxCapacity;
	}

	/**
	 * Returns the cargo object
	 * 
	 * @return cargo
	 */
	public Stock getCargo() {
		return cargo;
	}
}