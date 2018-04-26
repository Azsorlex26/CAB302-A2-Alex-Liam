package systemObjects;

import java.util.ArrayList;
import java.util.List;

public class Manifest { //A collection of trucks.

	List<Truck> manifest;

	public Manifest() {
		manifest = new ArrayList<Truck>();
	}

	public void add(Truck truck) {
		if (!manifest.contains(truck)) {
			manifest.add(truck);
		} else {
			System.out.println("That truck is already in the list.");
		}
	}
	
	public void remove(Truck truck) {
		try {
			manifest.remove(truck);
		} catch (Exception e) {
			System.out.println("That truck doesn't exist in the list.");
		}
	}
}