package assignment2.classes;

import java.util.ArrayList;
import java.util.List;

/*
 * A manifest is a collection of trucks
 */
public class Manifest {

	List<Truck> manifest;

	//Instantiates a new Manifest
	public Manifest() {
		manifest = new ArrayList<Truck>();
	}

	/*
	 * Adds a truck to the manifest
	 * @param truck
	 */
	public void add(Truck truck) {
		if (!manifest.contains(truck)) {
			manifest.add(truck);
		} else {
			System.out.println("That truck is already in the list.");
		}
	}
	
	/*
	 * Removes a truck from the manifest
	 * @param truck
	 */
	public void remove(Truck truck) {
		try {
			manifest.remove(truck);
		} catch (Exception e) {
			System.out.println("That truck doesn't exist in the list.");
		}
	}
}