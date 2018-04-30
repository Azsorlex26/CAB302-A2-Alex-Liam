package assignment2;

import java.util.ArrayList;
import java.util.List;

public abstract class Truck {

	protected int maxCapacity;
	protected List<Item> cargo = new ArrayList<Item>();;
	
	/*
	 * Adds items to the truck's cargo
	 * @param item
	 * @param quantity
	 */
	public void addItem(Item item, int quantity) {
		for (int i = 0; i < quantity; i++) {
			if (cargo.size() < maxCapacity) {
				cargo.add(item);
			} else {
				System.out.println("This truck is full. Items can no longer be added. " + i + " items were added.");
				break; // error handle for trying to add an item to a full truck
			}
		}
	}
	
	/*
	 * Removes items from the truck's cargo
	 * @param item
	 * @param quantity
	 */
	public void removeItem(Item item, int quantity) {
		for (int i = 0; i < quantity; i++) {
			try {
				cargo.remove(item);
			} catch (Exception e) {
				System.out.println("That item doesn't exist or no longer exists in the truck. " + i + " items were removed.");
				break; // error handle for trying to remove an item that doesn't exist
			}
		}
	}
	
	/*
	 * Gets the cost
	 */
	public double getCost;
	
	/*
	 * Gets the capacity
	 */
	public int getCapacity() { return maxCapacity; }
	
	/*
	 * Gets the the list of items in the cargo
	 */
	public List<Item> getCargo() { return cargo; }

}