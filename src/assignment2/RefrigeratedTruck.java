package assignment2;

public class RefrigeratedTruck extends Truck {

	private double temperature;
	
	public RefrigeratedTruck(double temperature) {
		this.temperature = temperature;
		maxCapacity = 800;
	}
	
	public double getCost() {
		return (900 + 200 * Math.pow(0.7, (temperature / 5.0)));
	}
}