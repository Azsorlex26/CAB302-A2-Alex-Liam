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
	 * @param truck
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
	 * Returns a list of trucks from the manifest
	 * 
	 * @return list of trucks
	 */
	public List<Truck> getTrucks() {
		return manifest;
	}
}