package assignment2;

import java.util.ArrayList;
import java.util.List;

public class Stock { //A collection of items representing store inventory, orders, sales logs and truck cargo
	
	List<Item> stock;
	
	/*
	 * Instantiate a new stock collection
	 */
	public Stock() { 
		stock = new ArrayList<Item>();
	}

	/*
	 * Adds an item to stock
	 * @param item
	 * @param quantity
	 */
	public void add(Item item, int quantity) {
		for (int i = 0; i < quantity; i++) {
			stock.add(item);
		}
	}
	
	/*
	 * Removes an item from stock
	 * @param item
	 * @param quantity
	 */
	public void remove(Item item, int quantity) {
		for (int i = 0; i < quantity; i++) {
			try {
				stock.remove(item);
			} catch (Exception e) {
				System.out.println("That item isn't in or is no longer in stock. " + i + " items were removed.");
				break; // error handle for trying to remove an item that doesn't exist
			}
		}
	}
}
