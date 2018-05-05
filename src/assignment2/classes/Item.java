package assignment2.classes;

/**
 * This class represents an item
 * 
 * @author Liam Edwards
 * @author Alexander Rozsa
 */
public class Item {

	private String name;
	private double manufactureCost, sellCost, tempThreshold; // tempThreshhold is the maximum temperature before the
														     // item perishes

	/**
	 * Instantiates a new item object
	 * 
	 * @param name
	 * @param manufactureCost
	 * @param sellCost
	 * @param tempThreshold
	 */
	public Item(String name, double manufactureCost, double sellCost, double tempThreshold) {
		this.name = name;
		this.manufactureCost = manufactureCost;
		this.sellCost = sellCost;
		this.tempThreshold = tempThreshold;
	}

	/**
	 * Get the name of the item
	 * 
	 * @return the name of the item
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the manufacturing cost of the item
	 * 
	 * @return manufactureCost
	 */
	public double getManufactureCost() {
		return manufactureCost;
	}

	/**
	 * Get the selling cost of the item
	 * 
	 * @return sellCost
	 */
	public double sellCost() {
		return sellCost;
	}

	/**
	 * Get the temperature threshold of the item
	 * 
	 * @return tempThreshold
	 */
	public double getTempThreshold() {
		return tempThreshold;
	}
}