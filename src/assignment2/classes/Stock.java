package assignment2.classes;

import java.util.Map;

import assignment2.exceptions.StockException;

import java.util.HashMap;

/**
 * This class represents a stock list
 * @author Liam Edwards
 * @author Alexander Rozsa
 */
public class Stock { // A collection of items representing store inventory, orders, sales logs and truck cargo

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
		if (stock.containsKey(item)) {
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
	public void remove(Item item, int quantity) throws StockException {
		if (quantity <= stock.get(item)) {
			stock.put(item, stock.get(item) - quantity); // Decrements the key's value
		} else {
			throw new StockException();
		}
		// To save memory in the application, remove the key if no stock is associated to it
		// This is covered in getQuantity where if no key is detected a 0 is returned
		if (stock.get(item) == 0) {
			stock.remove(item);
		}
	}

	/*
	 * Gets quantity of item
	 * @return item quantity
	 */
	public int itemQuantity(Item item) {
		if (stock.containsKey(item)) {
			return stock.get(item);
		}
		return 0;
	}

	/*
	 * Return the total quantity the stock list contains
	 * @return quantity of stock list
	 */
	public int totalQuantity() {
		int totalQuantity = 0;
		for (int quantity : stock.values()) {
			totalQuantity += quantity;
		}
		return totalQuantity;
	}
}