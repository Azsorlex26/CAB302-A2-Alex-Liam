package assignment2.classes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import assignment2.classes.truck.Truck;
import assignment2.exceptions.StockException;

/**
 * A manifest is a collection of trucks
 * 
 * @author Alexander Rozsa
 * @author Liam Edwards
 */
public class Manifest implements Iterable<Truck> {

	private List<Truck> manifest;

	/**
	 * Instantiates a new Manifest
	 */
	public Manifest() {
		manifest = new ArrayList<Truck>();
	}

	/**
	 * Adds a truck to the manifest
	 * 
	 * @param truck to be added
	 * @throws StockException
	 */
	public void add(Truck truck) throws StockException {
		if (!manifest.contains(truck)) {
			manifest.add(truck);
		} else {
			throw new StockException();
		}
	}
	
	/**
	 * Allows the manifest's items to be iterated through by only referencing the manifest
	 * 
	 * @return the manifest's iterator
	 */
	@Override
	public Iterator<Truck> iterator() {
		return manifest.iterator();
	}
}