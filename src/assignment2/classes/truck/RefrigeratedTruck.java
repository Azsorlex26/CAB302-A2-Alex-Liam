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

	private Double temperature;

	/**
	 * Constructs the RefrigeratedTruck object If temperature is outside of
	 * acceptable bounds, set to the nearest acceptable temperature
	 * 
	 * @param temperature
	 */
	public RefrigeratedTruck(double temperature) {
		setTemp(temperature);
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

	/**
	 * Sets the temperature
	 * 
	 * @param temperature
	 */
	public void setTemp(double temperature) {
		if (this.temperature == null || temperature < this.temperature || (temperature > -20 && temperature < 10)) {
			this.temperature = temperature;
		} else {
			if (temperature < -20) {
				this.temperature = -20.0;
			} else if (temperature > 10) {
				this.temperature = 10.0;
			}
		}
		/*
		 * if (temperature < -20) {
		 * 		this.temperature = -20.0;
		 * } else if (temperature > 10) {
		 * 		this.temperature = 10.0;
		 * } else {
		 * 		this.temperature = temperature;
		 * }
		 */
	}

	/**
	 * Gets the cost
	 * 
	 * @return cost
	 */
	@Override
	public double getCost() {
		return (900 + 200 * Math.pow(0.7, (temperature / 5.0)));
	}
}