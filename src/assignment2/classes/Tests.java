package assignment2.classes;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This class utilises JUnit to complete tests to
 * ensure integrity of the application
 * @author Liam Edwards
 * @author Alexander Rozsa
 */
public class Tests {

	@Test
	public void itemInitialize() {
		Item yogurt = new Item("Yogurt", 10, 20, 5);
		assertEquals(yogurt.getName(), "Yogurt");
	}

}