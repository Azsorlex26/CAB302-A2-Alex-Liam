package assignment2.classes;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import assignment2.classes.truck.OrdinaryTruck;
import assignment2.classes.truck.RefrigeratedTruck;
import assignment2.classes.truck.Truck;
import assignment2.exceptions.StockException;

/**
 * This class utilizes JUnit to complete tests to ensure integrity of the
 * application
 * 
 * @author Alexander Rozsa
 * @author Liam Edwards
 */
public class Tests {

	private static Store store, store2;
	private Item icecream = new Item("Ice-Cream", 2, 5, 1, 2, -5), beans = new Item("Canned Beans", 1, 2.5, 5, 10);
	private Truck ordTruck = new OrdinaryTruck(), refTruck = new RefrigeratedTruck(-20);

	@Before // Things to do before the tests
	public void initialiseTests() {
		store = Store.makeStore("UMart");
		ordTruck = new OrdinaryTruck();
		refTruck = new RefrigeratedTruck(-20);
	}

	@After // Things to do after the tests
	public void cleanUpTests() {
		Store.nullifyStore();
	}

	@Test
	public void itemInitial() { // Initializes object and tests if the values can be returned.
								// Same for all initialization tests.
		assertEquals("Ice-Cream", icecream.name());
		assertEquals(2, icecream.manufactureCost(), 0);
		assertEquals(5, icecream.sellCost(), 0);
		assertEquals(-5, icecream.tempThreshold(), 0);

		assertEquals("Canned Beans", beans.name());
		assertEquals(1, beans.manufactureCost(), 0);
		assertEquals(2.5, beans.sellCost(), 0);
		assertNull(beans.tempThreshold());
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
		ordTruck.add(beans, 5);
		assertEquals(1000, ordTruck.maxCapacity());
		assertEquals(5, ordTruck.cargo().totalQuantity());

		refTruck.add(icecream, 5);
		assertEquals(800, refTruck.maxCapacity());
		assertEquals(5, refTruck.cargo().totalQuantity());
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
	public void storeInitial() throws StockException {
		assertEquals("UMart", store.getName());
		store.adjustCapital(100);
		assertEquals(100100.0, store.capital(), 0);
		assertEquals(0, store.inventory().totalQuantity());
		store.inventory().add(beans, 10);
		assertEquals(10, store.inventory().totalQuantity());
	}

	@Test
	public void onlyOneStore() {
		store2 = Store.makeStore("Coals");
		assertEquals(store.getName(), store2.getName());
		store.adjustCapital(-100);
		assertEquals(store.capital(), store2.capital(), 0);
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

	@Test(expected = StockException.class)
	public void removeFromEmptyManifest() throws StockException {
		Manifest trucks = new Manifest();
		trucks.remove(ordTruck);
	}

	@Test(expected = StockException.class)
	public void addDuplicateItemsToStock() throws StockException {
		Item item1 = new Item("", 10, 20, 30, 40);
		Item item2 = new Item("", 10, 20, 30, 40);
		store.inventory().add(item1, 10);
		store.inventory().add(item2, 20); // This will fail
	}

	@Test(expected = StockException.class)
	public void removeItemFromEmptyStore() throws StockException {
		store.inventory().remove(beans, 10); // This will fail
	}

	@Test(expected = StockException.class)
	public void removeTooManyItemsFromStore() throws StockException {
		store.inventory().add(beans, 10);
		store.inventory().remove(beans, 20); // This will fail
	}

	@Test(expected = StockException.class)
	public void addingTooMuchToTruck() throws StockException {
		ordTruck.add(beans, 1001); // This will fail
	}

	@Test
	public void reorder() throws StockException {
		// Add beans to the beans reorder amount and restock. Output is 15
		store.inventory().add(beans, 5);
		assertEquals(5, store.inventory().totalQuantity());
		store.restock();
		assertEquals(15, store.inventory().totalQuantity());

		// Remove all items and try to restock. Output is 0 since the key got removed
		store.inventory().remove(beans, 15);
		store.restock();
		assertEquals(0, store.inventory().totalQuantity());
	}
}