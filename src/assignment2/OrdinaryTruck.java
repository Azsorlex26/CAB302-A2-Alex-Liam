package assignment2;

import java.util.ArrayList;
import java.util.List;

public class OrdinaryTruck implements Truck { // A truck with properties (view specification)

	private int maxCapacity;
	private List<Item> cargo;

	public OrdinaryTruck() {
		this.maxCapacity = 1000;
		cargo = new ArrayList<Item>();
	}

	@Override
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

	@Override
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

	@Override
	public double getCost() {
		return (750 + (0.25 * cargo.size()));
	}

	@Override
	public int getCapacity() {
		return maxCapacity;
	}

	@Override
	public List<Item> getCargo() {
		return cargo;
	}

}
