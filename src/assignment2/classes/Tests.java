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
	public void itemInitial() { // Initializes object and tests if the values can be returned. Same for all
								// initialization tests
		assertEquals("Ice-Cream", icecream.getName());
		assertEquals(2, icecream.getManufactureCost(), 0);
		assertEquals(5, icecream.sellCost(), 0);
		assertEquals(-5, icecream.getTempThreshold(), 0);

		assertEquals("Canned Beans", beans.getName());
		assertEquals(1, beans.getManufactureCost(), 0);
		assertEquals(2.5, beans.sellCost(), 0);
		assertNull(beans.getTempThreshold());
	}

	@Test
	public void stockInitial() throws StockException {
		Stock stock = new Stock();
		stock.add(beans, 10);
		assertEquals(10, stock.itemQuantity(beans));
		assertEquals(10, stock.totalQuantity());
		stock.add(beans, 5);
		assertEquals(15, stock.itemQuantity(beans));
		stock.remove(beans, 10);
		assertEquals(5, stock.itemQuantity(beans));
		assertTrue(stock.contains(beans));
		stock.remove(beans, 5);
		assertFalse(stock.contains(beans));
	}

	@Test
	public void trucksInitial() throws StockException {
		Truck o = new OrdinaryTruck();
		o.add(new Item("Test1", 10, 20), 5);
		assertEquals(1000, o.maxCapacity());
		assertEquals(5, o.cargo().totalQuantity());

		Truck r = new RefrigeratedTruck(0);
		r.add(new Item("Test2", 20, 40, 10), 5);
		assertEquals(800, r.maxCapacity());
		assertEquals(5, r.cargo().totalQuantity());
	}

	@Test
	public void manifestInitial() throws StockException {
		Manifest trucks = new Manifest();
		trucks.add(ordTruck);
		trucks.add(refTruck);
		assertEquals(2, trucks.totalTrucks());
		trucks.remove(ordTruck);
		assertEquals(1, trucks.totalTrucks());
		trucks.clear();
		assertEquals(0, trucks.totalTrucks());
	}

	@Test
	public void storeInitial() {
		assertEquals(null, Store.getStore());
		store = Store.makeStore("UMart");
		assertEquals("UMart", store.getName());
		store.addCapital(100);
		assertEquals(100100.0, store.capital(), 0);
		assertEquals(store, Store.getStore());
		assertEquals(0, store.inventory().totalQuantity());
		Store.nullifyStore(); // Reset the Store variable
	}

	@Test
	public void onlyOneStore() {
		store = Store.makeStore("UMart");
		store2 = Store.makeStore("WalMart");
		assertEquals(store.getName(), store2.getName());
		store.addCapital(100);
		assertEquals(store.capital(), store2.capital(), 0);
		Store.nullifyStore(); // Reset the Store variable
	}

	@Test
	public void returningNullTempFromDryItem() { // Attempts to get a threshold from an item without a threshold
		assertNull(beans.getTempThreshold());
	}

	@Test(expected = StockException.class)
	public void addItemsToOrdTruck() throws StockException {
		Truck ordTruck = new OrdinaryTruck();
		ordTruck.add(beans, 1); // This will work
		ordTruck.add(icecream, 1); // This will fail
	}

	@Test
	public void addItemsToRefTruck() throws StockException {
		Truck refTruck = new RefrigeratedTruck(-20);
		refTruck.add(beans, 1); // Both of these will work
		refTruck.add(icecream, 1);
		assertEquals(2, refTruck.cargo().totalQuantity());
	}

	@Test(expected = StockException.class)
	public void addExistingTruckToManifest() throws StockException {
		Manifest trucks = new Manifest();
		trucks.add(ordTruck);
		trucks.add(ordTruck); // This will fail
	}

	@Test
	public void removeFromEmptyManifest() throws StockException {
		Manifest trucks = new Manifest();
		trucks.remove(ordTruck); // It doesn't matter
	}
}