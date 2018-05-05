package assignment2.classes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import assignment2.classes.truck.OrdinaryTruck;

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
		assertEquals(store, Store.getStore());
	}

	@Test
	public void onlyOneStore() {
		List<Store> storeStore = new ArrayList<Store>();
		Store store1 = Store.makeStore("UMart");
		storeStore.add(store1);

		Store store2 = Store.makeStore("WalMart");
		storeStore.add(store2);
		store1.addCapital(-100);
		System.out.println(store1.getCapital() + "" + store2.getCapital());
		// assertEquals(1, storeStore.size());
	}
}