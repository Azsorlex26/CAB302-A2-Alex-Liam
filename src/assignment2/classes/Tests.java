package assignment2.classes;

import static org.junit.Assert.*;
import org.junit.Test;
import assignment2.classes.truck.OrdinaryTruck;

/**
 * This class utilises JUnit to complete tests to ensure integrity of the
 * application
 * @author Liam Edwards
 * @author Alexander Rozsa
 */
public class Tests {

	@Test
	public void itemInitialize() {
		Item yogurt = new Item("Yogurt", 10, 20, 5);
		assertEquals(yogurt.getName(), "Yogurt");
	}

	@Test
	public void manifestInitialize() {
		Manifest trucks = new Manifest();
		trucks.add(new OrdinaryTruck());
	}
	
	@Test
	public void storeInitialize() {
		assertEquals(Store.getStore(), null);
		Store store = Store.makeStore("WalMart");
		assertEquals(store.getName(), "WalMart");
		assertEquals(Store.getStore(), store);
	}
}