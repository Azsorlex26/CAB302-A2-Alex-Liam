package assignment2.classes;

import java.util.Map;
import java.util.NoSuchElementException;
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
	 * @param item to use as key
	 * @param quantity to be added (Can be 0)   
	 * @throws StockException
	 */
	public void add(Item item, int quantity) throws StockException {
		if (quantity >= 0) {
			for (Item existingItem : stock.keySet()) {
				if (existingItem.getName() == item.getName()) {
					stock.put(existingItem, stock.get(existingItem) + quantity);
					return;
				}
			}
			stock.put(item, quantity);
			
		} else if (item == null) {
			throw new StockException("Null item");
		} else {
			throw new StockException("Negative amount");
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
		if (stock.containsKey(item)) {
			if (quantity <= stock.get(item)) {
				stock.put(item, stock.get(item) - quantity); // Decrements the key's value
			} else {
				throw new StockException("Not enough items");
			}
		} else {
			throw new StockException("Doesn't exist");
		}
	}

	/**
	 * Gets quantity of item
	 * 
	 * @param item
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
	 * @param item
	 * @return if the item quantity is <= to the item's reorder point
	 */
	public boolean reorder(Item item) {
		return stock.get(item) <= item.getReorderPoint();
	}

	@Override
	public Iterator<Item> iterator() {
		return new Iterator<Item>() {
			Object[] items = stock.keySet().toArray();
			int current = 0;

			@Override
			public boolean hasNext() {
				return current < items.length;
			}

			@Override
			public Item next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				return (Item) items[current++];
			}
		};
	}
}