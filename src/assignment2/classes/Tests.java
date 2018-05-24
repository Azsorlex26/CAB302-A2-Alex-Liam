package assignment2.classes;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import assignment2.classes.truck.OrdinaryTruck;
import assignment2.classes.truck.RefrigeratedTruck;
import assignment2.classes.truck.Truck;
import assignment2.exceptions.CSVFormatException;
import assignment2.exceptions.DeliveryException;
import assignment2.exceptions.StockException;

/**
 * This class utilizes JUnit to complete tests to ensure integrity of the
 * application
 * 
 * @author Alexander Rozsa
 * @author Liam Edwards
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Tests {

	private Item icecream = new Item("Ice-Cream", 2, 5, 1, 2, -5), beans = new Item("Canned Beans", 1, 2.5, 5, 10);
	private Truck ordTruck = new OrdinaryTruck(), refTruck;
	private String filePath;

	private void importFileProperties() throws CSVFormatException, IOException {
		while ((filePath = IOHandler.fileChooser(false)) == null);
		IOHandler.readItemProperties(filePath);
	}
	
	@Before // Things to do before the tests
	public void initialiseTests() throws DeliveryException {
		Store.makeStore("UMart");
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
		assertEquals("Ice-Cream", icecream.getName());
		assertEquals(2, icecream.getManufactureCost(), 0);
		assertEquals(5, icecream.getSellCost(), 0);
		assertEquals(-5, icecream.getTempThreshold(), 0);

		assertEquals("Canned Beans", beans.getName());
		assertEquals(1, beans.getManufactureCost(), 0);
		assertEquals(2.5, beans.getSellCost(), 0);
		assertNull(beans.getTempThreshold());
	}

	@Test
	public void stockInitial() throws StockException {
		Stock stock = new Stock();
		stock.add(beans, 10);
		assertEquals(10, stock.getItemQuantity("Canned Beans"));
		assertEquals(10, stock.totalQuantity());
		stock.add(beans, 5);
		assertEquals(15, stock.getItemQuantity("Canned Beans"));
		stock.remove(beans, 10);
		assertEquals(5, stock.getItemQuantity("Canned Beans"));
		assertTrue(stock.contains("Canned Beans"));
		stock.remove(beans, 5);
		assertEquals(0, stock.getItemQuantity("Canned Beans"));
	}

	@Test
	public void trucksInitial() throws StockException {
		ordTruck.add(beans, 5);
		assertEquals(1000, ordTruck.maxCapacity());
		assertEquals(5, ordTruck.getCargo().totalQuantity());

		refTruck.add(icecream, 5);
		assertEquals(800, refTruck.maxCapacity());
		assertEquals(5, refTruck.getCargo().totalQuantity());
	}

	@Test
	public void manifestInitial() throws StockException {
		Manifest trucks = new Manifest();
		trucks.add(ordTruck);
		trucks.add(refTruck);
		assertEquals(2, trucks.getTotalTrucks());
		trucks.remove(ordTruck);
		assertEquals(1, trucks.getTotalTrucks());
		trucks.clear();
		assertEquals(0, trucks.getTotalTrucks());
	}

	@Test
	public void storeInitial() throws StockException {
		assertEquals("UMart", Store.getName());
		Store.adjustCapital(100);
		assertEquals(100100.0, Store.getCapital(), 0);
		assertEquals(0, Store.getInventory().totalQuantity());
		Store.getInventory().add(beans, 10);
		assertEquals(10, Store.getInventory().totalQuantity());
	}

	@Test
	public void onlyOneStore() {
		Store.makeStore("Coals");
		assertEquals(Store.getName(), Store.getName());
		Store.adjustCapital(-100);
		assertEquals(Store.getCapital(), Store.getCapital(), 0);
	}

	@Test(expected = StockException.class)
	public void addItemsToOrdTruck() throws StockException {
		Truck ordTruck = new OrdinaryTruck();
		ordTruck.add(beans, 1); // This will work
		ordTruck.add(icecream, 1); // This will fail
	}

	@Test
	public void addItemsToRefTruck() throws StockException, DeliveryException {
		Truck refTruck = new RefrigeratedTruck(-20);
		refTruck.add(beans, 1); // Both of these will work
		refTruck.add(icecream, 1);
		assertEquals(2, refTruck.getCargo().totalQuantity());
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

	@Test
	public void addDuplicateItemsToStock() throws StockException {
		Item item1 = new Item("Jelly Beans", 10, 20, 30, 40);
		Item item2 = new Item("Jelly Beans", 10, 20, 30, 40);
		Store.getInventory().add(item1, 10);
		Store.getInventory().add(item2, 20); // This will fail
		assertEquals(30, Store.getInventory().getItemQuantity("Jelly Beans"));
	}

	@Test(expected = StockException.class)
	public void removeItemFromEmptyStore() throws StockException {
		Store.getInventory().remove(beans, 10); // This will fail
	}

	@Test(expected = StockException.class)
	public void removeTooManyItemsFromStore() throws StockException {
		Store.getInventory().add(beans, 10);
		Store.getInventory().remove(beans, 20); // This will fail
	}

	@Test(expected = StockException.class)
	public void addingTooMuchToTruck() throws StockException {
		ordTruck.add(beans, 1001); // This will fail
	}

	//For the next three tests, an item MUST be chosen
	@Test //Import item_properties.csv, or this will fail.
	public void readItemProperties() throws CSVFormatException, IOException {
		importFileProperties();
		assertEquals(24, Store.getInventory().totalItems());
	}

	@Test //Import manifest.csv, or this will fail.
	public void readManifest() throws CSVFormatException, StockException, IOException {
		while ((filePath = IOHandler.fileChooser(false)) == null);
		IOHandler.readManifest(filePath);
		assertEquals(325, Store.getInventory().totalQuantity());
	}

	@Test //Import sales_log_0.csv, or this will fail.
	public void readSalesLog() throws CSVFormatException, StockException, IOException {
		while ((filePath = IOHandler.fileChooser(false)) == null);
		IOHandler.readSalesLog(filePath);
		assertTrue(Store.getCapital() > 100000);
	}
	
	@Test
	public void exportManifest() throws CSVFormatException, IOException, StockException {
		importFileProperties();
		IOHandler.exportManifest(null); //This might need to be changed
	}
}