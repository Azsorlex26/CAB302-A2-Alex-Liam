package assignment2.classes;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

import assignment2.exceptions.StockException;

/**
 * This class represents a collection of items representing store inventory,
 * orders, sales logs and truck cargo.
 * 
 * @author Liam Edwards
 * @author Alexander Rozsa
 */
public class Stock implements Iterable<Item> {

	private Map<Item, Integer> stock;

	/**
	 * Instantiate a new stock collection
	 */
	public Stock() {
		stock = new HashMap<Item, Integer>();
	}

	/**
	 * Adds items to stock
	 * 
	 * @param item to be added
	 * @param quantity to be added (can be 0)
	 * @throws StockException if the item is null or the quantity is negative
	 */
	public void add(Item item, int quantity) throws StockException {
		if (item != null && quantity >= 0) {
			for (Item existingItem : stock.keySet()) {
				if (item.getName() == existingItem.getName()) {
					stock.put(existingItem, stock.get(existingItem) + quantity);
					return;
				}
			}
			stock.put(item, quantity);
		} else {
			throw new StockException();
		}
	}

	/**
	 * Removes items from stock
	 * 
	 * @param item to be removed
	 * @param quantity of item to be removed
	 * @throws StockException if item isn't in the map or quantity is greater than the amount of the item in the map
	 */
	public void remove(Item item, int quantity) throws StockException {
		if (stock.containsKey(item) && quantity <= stock.get(item)) {
			stock.put(item, stock.get(item) - quantity); // Decrements the key's value
		} else {
			throw new StockException();
		}
	}

	/**
	 * Iterates through all items in store to locate the Item by that name
	 * 
	 * @param name of the item to retrieve
	 * @return the item specified
	 * @throws StockException if item doesn't exist in the map
	 */
	public Item getItem(String name) throws StockException {
		for (Item item : stock.keySet()) {
			if (item.getName().equalsIgnoreCase(name)) {
				return item;
			}
		}
		throw new StockException();
	}

	/**
	 * Returns the total number of keys in the map
	 * 
	 * @return number of items
	 */
	public int totalItems() {
		return stock.keySet().size();
	}

	/**
	 * Gets quantity of item
	 * 
	 * @param name of item to get quantity of
	 * @return quantity of specific item
	 */
	public int getItemQuantity(String name) {
		for (Item item : stock.keySet()) {
			if (item.getName() == name) {
				return stock.get(item);
			}
		}
		return 0;
	}

	/**
	 * Gets quantity of item
	 * 
	 * @param item to check quantity of
	 * @return quantity of specific item
	 */
	public int getItemQuantity(Item item) {
		return stock.get(item);
	}

	/**
	 * Return the total quantity of items the stock list contains
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
	 * @param name of the item
	 * @return true if the item is in stock. false otherwise
	 */
	public boolean contains(String name) {
		for (Item item : stock.keySet()) {
			if (item.getName() == name) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns if the item needs to be reordered or not
	 * 
	 * @param item to be checked
	 * @return if the item quantity is <= to the item's reorder point
	 */
	public boolean reorder(Item item) {
		return stock.get(item) <= item.getReorderPoint();
	}

	/**
	 * Allows the stock's items to be iterated through by only referencing the store
	 */
	@Override
	public Iterator<Item> iterator() {
		return stock.keySet().iterator();
	}
}