package assignment2.classes;

import java.util.ArrayList;
import java.util.List;

import assignment2.classes.truck.Truck;
import assignment2.exceptions.StockException;

/**
 * A manifest is a collection of trucks
 * 
 * @author Alexander Rozsa
 * @author Liam Edwards
 */
public class Manifest {

	List<Truck> manifest;

	/**
	 * Instantiates a new Manifest
	 */
	public Manifest() {
		manifest = new ArrayList<Truck>();
	}

	/**
	 * Adds a truck to the manifest
	 * 
	 * @param truck
	 * @throws StockException
	 */
	public void add(Truck truck) throws StockException {
		if (!manifest.contains(truck)) {
			manifest.add(truck);
		} else {
			throw new StockException("That truck is already in this manifest.");
		}
	}

	/**
	 * Removes a truck from the manifest
	 * 
	 * @param truck
	 * @throws StockException
	 */
	public void remove(Truck truck) throws StockException {
		if (!manifest.remove(truck)) { // If an item doesn't get removed, throw exception
			throw new StockException("That truck doesn't exist in this manifest.");
		}
	}

	/**
	 * Returns the total number of trucks in the manifest
	 * 
	 * @return number of trucks
	 */
	public int getTotalTrucks() {
		return manifest.size();
	}

	/**
	 * Removes all trucks from the manifest
	 */
	public void clear() {
		manifest.clear();
	}
}