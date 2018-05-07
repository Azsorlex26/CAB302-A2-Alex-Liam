package assignment2.classes.truck;

import assignment2.classes.Item;
import assignment2.exceptions.StockException;

/**
 * This class represents a refrigerated truck
 * 
 * @author Liam Edwards
 * @author Alexander Rozsa
 */
public class RefrigeratedTruck extends Truck {

	private double temperature;

	/**
	 * Constructs the RefrigeratedTruck object
	 * 
	 * @param temperature
	 */
	public RefrigeratedTruck(double temperature) {
		this.temperature = temperature;
		maxCapacity = 800;
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
		if (item.getTempThreshold() == null || temperature <= item.getTempThreshold()) {
			super.add(item, quantity);
		} else {
			throw new StockException();
		}
	}
	//The temperature of the truck has to be less
	//than the item perish temp for it to be added.
	//If it is over, the item will perish.

	/**
	 * Gets the cost
	 * 
	 * @return cost
	 */
	public double getCost() {
		return (900 + 200 * Math.pow(0.7, (temperature / 5.0)));
	}
}