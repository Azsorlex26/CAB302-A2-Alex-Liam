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
	
	private Item icecream = new Item("Ice-Cream", 2, 5, -5);
	private Item beans = new Item("Canned Beans", 1, 2.5);

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
		stock.add(beans, 50);
		assertEquals(50, stock.totalQuantity());
	}
	
	@Test
	public void trucksInitialize() throws StockException {
		Truck ordTruck = new OrdinaryTruck();
		Truck refTruck = new RefrigeratedTruck(-20);
		assertEquals(1000, ordTruck.getCapacity());
		assertEquals(800, refTruck.getCapacity());
	}

	@Test
	public void onlyOneStore() {
		Store store1 = Store.makeStore("UMart");
		Store store2 = Store.makeStore("WalMart");
		assertEquals(store1.getName(), store2.getName());
		assertEquals(store1.getCapital(), store2.getCapital());
		Store.nullifyStore(); //Reset the Store variable
	}
	
	@Test
	public void returningTempFromDryItem() { //Attempts to get a threshold from an item without a threshold
		Item beans = new Item("Beans", 1, 2.5);
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
		
		refTruck.add(icecream, 1); //Both of these will work
		refTruck.add(beans, 1);
	}
}