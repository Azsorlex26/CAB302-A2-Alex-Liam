package assignment2.classes;

import static org.junit.Assert.*;

import org.junit.Test;
import assignment2.classes.truck.OrdinaryTruck;
import assignment2.classes.truck.RefrigeratedTruck;
import assignment2.classes.truck.Truck;
import assignment2.exceptions.StockException;

/**
 * This class utilizes JUnit to complete tests to ensure integrity of the
 * application
 * 
 * @author Liam Edwards
 * @author Alexander Rozsa
 */
public class Tests {

	@Test
	public void itemInitialize() {
		Item yogurt = new Item("Yogurt", 10, 20, 5);
		assertEquals("Yogurt", yogurt.getName());
	}

	@Test
	public void manifestInitialize() {
		Manifest trucks = new Manifest();
		trucks.add(new OrdinaryTruck());
	}

	@Test
	public void storeInitialize() {
		assertEquals(null, Store.getStore());
		Store store = Store.makeStore("WalMart");
		assertEquals("WalMart", store.getName());
		Store.nullifyStore(); //Reset the Store variable
	}
	
	@Test
	public void stockInitialize() {
		Stock stock = new Stock();
		Item blueberries = new Item("Blueberries", 1, 5, 30);
		stock.add(blueberries, 50);
		assertEquals(50, stock.totalQuantity());
	}
	
	@Test
	public void trucksInitialize() throws StockException {
		Truck ordTruck = new OrdinaryTruck();
		Truck refTruck = new RefrigeratedTruck(10);
		Item icecream = new Item("Ice-Cream", 2, 5, -15); 
		ordTruck.add(icecream, 5);
		refTruck.add(icecream, 2);
		assertEquals(50, ordTruck.getCargo().totalQuantity());
		assertEquals(10, refTruck.getCargo().totalQuantity());
	}

	@Test
	public void onlyOneStore() {
		Store store1 = Store.makeStore("UMart");
		Store store2 = Store.makeStore("WalMart");
		assertEquals(store1.getName(), store2.getName());
		assertEquals(store1.getCapital(), store2.getCapital());
		Store.nullifyStore(); //Reset the Store variable
	}
	
	
}