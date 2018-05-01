package assignment2;

public class RefrigeratedTruck extends Truck {

	private double temperature;
	
	/*
	 * Constructs the RefrigeratedTruck object
	 * @param temperature
	 */
	public RefrigeratedTruck(double temperature) {
		this.temperature = temperature;
		maxCapacity = 800;
	}
	
	/*
	 * Gets the cost
	 * @return cost
	 */
	public double getCost() {
		return (900 + 200 * Math.pow(0.7, (temperature / 5.0)));
	}
}