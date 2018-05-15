package assignment2.classes;

/**
 * This class represents an item
 * 
 * @author Liam Edwards
 * @author Alexander Rozsa
 */
public class Item {

	private String name;
	private double manufactureCost, sellCost;
	private int reorderPoint, reorderAmount;
	private Double tempThreshold; // tempThreshhold is the maximum temperature before the item perishes

	/**
	 * Instantiates a new item object without a temperature threshold
	 * 
	 * @param name
	 * @param manufactureCost
	 * @param sellCost
	 * @param reorderPoint
	 * @param reorderAmount
	 */
	public Item(String name, double manufactureCost, double sellCost, int reorderPoint, int reorderAmount) {
		this.name = name;
		this.manufactureCost = manufactureCost;
		this.sellCost = sellCost;
		this.reorderPoint = reorderPoint;
		this.reorderAmount = reorderAmount;
	}

	/**
	 * Instantiates a new item object with a temperature threshold
	 * 
	 * @param name
	 * @param manufactureCost
	 * @param reorderPoint
	 * @param reorderAmount
	 * @param sellCost
	 * @param tempThreshold
	 */
	public Item(String name, double manufactureCost, double sellCost, int reorderPoint, int reorderAmount, double tempThreshold) {
		this.name = name;
		this.manufactureCost = manufactureCost;
		this.sellCost = sellCost;
		this.reorderPoint = reorderPoint;
		this.reorderAmount = reorderAmount;
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
	 * Get the reorder point of the item
	 * 
	 * @return sellCost
	 */
	public int reorderPoint() {
		return reorderPoint;
	}
	
	/**
	 * Get the reorder amount of the item
	 * 
	 * @return sellCost
	 */
	public int reorderAmount() {
		return reorderAmount;
	}

	/**
	 * Get the temperature threshold of the item. Can return null
	 * 
	 * @return tempThreshold
	 */
	public Double getTempThreshold() {
		return tempThreshold;
	}
}