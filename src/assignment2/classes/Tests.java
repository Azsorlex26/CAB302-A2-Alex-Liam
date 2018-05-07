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
	
	private static Store store, store2;
	private Item icecream = new Item("Ice-Cream", 2, 5, -5), beans = new Item("Canned Beans", 1, 2.5);
	private Truck ordTruck = new OrdinaryTruck(), refTruck = new RefrigeratedTruck(-20);

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
		store = Store.makeStore("UMart");
		assertEquals("UMart", store.getName());
		Store.nullifyStore(); //Reset the Store variable
	}
	
	@Test
	public void stockInitialize() {
		Stock stock = new Stock();
		stock.add(beans, 50);
		assertEquals(50, stock.totalQuantity());
	}
	
	@Test
	public void trucksInitialize() throws StockException {
		assertEquals(1000, ordTruck.getCapacity());
		assertEquals(800, refTruck.getCapacity());
	}

	@Test
	public void onlyOneStore() {
		store = Store.makeStore("UMart");
		store2 = Store.makeStore("WalMart");
		assertEquals(store.getName(), store2.getName());
		store.addCapital(100);
		assertEquals(store.getCapital(), store2.getCapital());
		Store.nullifyStore(); //Reset the Store variable
	}
	
	@Test
	public void returningTempFromDryItem() { //Attempts to get a threshold from an item without a threshold
		assertNull(beans.getTempThreshold());
	}
	
	@Test(expected = StockException.class)
	public void addItemsToOrdTruck() throws StockException {
		Truck ordTruck = new OrdinaryTruck();
		ordTruck.add(beans, 1); //This will work
		ordTruck.add(icecream, 1); //This will fail
	}
	
	@Test
	public void addItemsToRefTruck() throws StockException {
		Truck refTruck = new RefrigeratedTruck(-20);
		refTruck.add(beans, 1); //Both of these will work
		refTruck.add(icecream, 1);
	}
}