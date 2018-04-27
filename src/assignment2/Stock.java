package assignment2;

import java.util.ArrayList;
import java.util.List;

public class Stock { //A collection of items representing store inventory, orders, sales logs and truck cargo
	
	List<Item> stock;
	
	public Stock() { 
		stock = new ArrayList<Item>();
	}

	public void add(Item item, int quantity) {
		for (int i = 0; i < quantity; i++) {
			stock.add(item);
		}
	}
	
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
