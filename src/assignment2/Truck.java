package assignment2;

import java.util.List;

public interface Truck { //An abstract class for the two truck types
	public void addItem(Item item, int quantity);
	public void removeItem(Item item, int quantity);
	public double getCost();
	public int getCapacity();
	public List<Item> getCargo();
}
