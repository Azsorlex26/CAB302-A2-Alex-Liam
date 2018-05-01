package assignment2;

import java.util.Map;
import java.util.HashMap;

public class Stock { //A collection of items representing store inventory, orders, sales logs and truck cargo
	
	Map<Item, Integer> stock;
	
	/*
	 * Instantiate a new stock collection
	 */
	public Stock() { 
		stock = new HashMap<Item, Integer>();
	}

	/*
	 * Adds items to stock
	 * @param item
	 * @param quantity
	 */
	public void add(Item item, int quantity) {
		if(stock.containsKey(item)) {
			stock.put(item, stock.get(item) + quantity);
		} else {
			stock.put(item, quantity);
		}
	}
	
	/*
	 * Removes items from stock
	 * @param item
	 * @param quantity
	 */
	public void remove(Item item, int quantity) {
		for (int i = 0; i < quantity; i++) {
			if (quantity <= stock.get(item)) {
				stock.put(item, stock.get(item) - 1); //Decrements the key's value
			} else {
				System.out.println("There aren't that many items in the list. Unable to remove.");
				return; //Exits the function
			}
		}
		
		if (stock.get(item) == 0) { //Removes the key if 0 is associated to it
			stock.remove(item);
		}
	}
}
