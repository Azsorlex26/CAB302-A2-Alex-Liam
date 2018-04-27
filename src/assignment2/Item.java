package assignment2;

public class Item { //An item

	private String name;
	private double manufactureCost, sellCost, tempThreshold; //tempThreshhold is the maximum temperature before the item perishes
	
	public Item(String name, double manufactureCost, double sellCost, double tempThreshold) {
		this.name = name;
		this.manufactureCost = manufactureCost;
		this.sellCost = sellCost;
		this.tempThreshold = tempThreshold;
	}

	public String getName() {
		return name;
	}
	
	public double getManufactureCost() {
		return manufactureCost;
	}
	
	public double sellCost() {
		return sellCost;
	}
	
	public double getTempThreshold() {
		return tempThreshold;
	}
}