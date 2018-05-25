package assignment2.classes;

/**
 * This class represents an item
 * 
 * @author Alexander Rozsa
 * @author Liam Edwards
 */
public class Item implements Comparable<Item> {

	private String name;
	private double manufactureCost, sellCost;
	private int reorderPoint, reorderAmount;
	private Double tempThreshold; // tempThreshhold is the maximum temperature before the item perishes

	/**
	 * Instantiates a new item object without a temperature threshold
	 * 
	 * @param name of the item
	 * @param manufactureCost of the item
	 * @param sellCost of the item
	 * @param reorderPoint of the item
	 * @param reorderAmount of the item
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
	 * @param name of the item
	 * @param manufactureCost of the item
	 * @param sellCost of the item
	 * @param reorderPoint of the item
	 * @param reorderAmount of the item
	 * @param tempThreshold of the item
	 */
	public Item(String name, double manufactureCost, double sellCost, int reorderPoint, int reorderAmount, double tempThreshold) {
		this(name, manufactureCost, sellCost, reorderPoint, reorderAmount);
		this.tempThreshold = tempThreshold;
	}

	/**
	 * Get the name of the item
	 * 
	 * @return name of the item
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the manufacturing cost of the item
	 * 
	 * @return manufactureCost  of the item
	 */
	public double getManufactureCost() {
		return manufactureCost;
	}

	/**
	 * Get the selling cost of the item
	 * 
	 * @return sellCost of the item
	 */
	public double getSellCost() {
		return sellCost;
	}
	
	/**
	 * Get the reorder point of the item
	 * 
	 * @return reorderPoint of the item
	 */
	public int getReorderPoint() {
		return reorderPoint;
	}
	
	/**
	 * Get the reorder amount of the item
	 * 
	 * @return reorderAmount of the item
	 */
	public int getReorderAmount() {
		return reorderAmount;
	}

	/**
	 * Get the temperature threshold of the item. Can return null
	 * 
	 * @return tempThreshold of the item
	 */
	public Double getTempThreshold() {
		return tempThreshold;
	}

	/**
	 * Allows items to be ordered by tempThreshold in Stock
	 * 
	 * @param other item to be compared
	 * @return if item is greater than, less than, or equal to the other
	 */
	@Override
	public int compareTo(Item other) {
		if (tempThreshold == null || other.getTempThreshold() == null) return 0;
		return Double.compare(tempThreshold, other.tempThreshold);
	}
}