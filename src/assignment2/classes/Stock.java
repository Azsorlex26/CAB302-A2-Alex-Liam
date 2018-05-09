package assignment2.classes;

import java.util.Map;
import java.util.HashMap;

import assignment2.exceptions.StockException;

/**
 * This class represents a A collection of items representing store inventory,
 * orders, sales logs and truck cargo.
 * 
 * @author Liam Edwards
 * @author Alexander Rozsa
 */
public class Stock { //

	Map<Item, Integer> stock;

	/**
	 * Instantiate a new stock collection
	 */
	public Stock() {
		stock = new HashMap<Item, Integer>();
	}

	/**
	 * Adds items to stock
	 * 
	 * @param item
	 * @param quantity
	 */
	public void add(Item item, int quantity) {
		if (stock.containsKey(item)) {
			stock.put(item, stock.get(item) + quantity);
		} else {
			stock.put(item, quantity);
		}
	}

	/**
	 * Removes items from stock
	 * 
	 * @param item
	 * @param quantity
	 * @throws StockException
	 */
	public void remove(Item item, int quantity) throws StockException {
		if (quantity < stock.get(item)) {
			stock.put(item, stock.get(item) - quantity); // Decrements the key's value
		} else if (stock.get(item) == quantity) { // Remove key if the remove quantity is the same as the amount
			stock.remove(item);
		} else {
			throw new StockException();
		}
	}

	/**
	 * Gets quantity of item
	 * 
	 * @param item
	 * @return quantity of specific item
	 */
	public int itemQuantity(Item item) {
		if (stock.containsKey(item)) {
			return stock.get(item);
		}
		return 0;
	}

	/**
	 * Return the total quantity the stock list contains
	 * 
	 * @return quantity of all items in stock
	 */
	public int totalQuantity() {
		int totalQuantity = 0;
		for (int quantity : stock.values()) {
			totalQuantity += quantity;
		}
		return totalQuantity;
	}

	/**
	 * Returns if the hashmap contains an item or not
	 * 
	 * @param item
	 * @return true if the item is in stock
	 */
	public boolean contains(Item item) {
		return stock.containsKey(item);
	}
}