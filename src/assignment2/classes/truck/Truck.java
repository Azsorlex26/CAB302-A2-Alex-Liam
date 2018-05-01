package assignment2.classes.truck;

import assignment2.classes.Item;
import assignment2.classes.Stock;
import assignment2.exceptions.StockException;

/**
 * This abstract class is the superclass for the two types of trucks
 * @author Liam Edwards
 * @author Alexander Rozsa
 */
public abstract class Truck {

	protected int maxCapacity;
	protected Stock cargo = new Stock();
	
	/*
	 * Adds items to the truck's cargo
	 * @param item
	 * @param quantity
	 */
	public void add(Item item, int quantity) throws StockException {
		if ((cargo.totalQuantity() + quantity) <= maxCapacity) {
			cargo.add(item, quantity);
		} else {
			throw new StockException();
		}
	}
	
	/*
	 * Gets the cost
	 */
	public double getCost;
	
	/*
	 * Gets the capacity
	 * @return maxCapacity
	 */
	public int getCapacity() { 
		return maxCapacity; 
	}
	
	/*
	 * Gets the the list of items in the cargo
	 * @return cargo
	 */
	public Stock getCargo() { 
		return cargo; 
	}

}