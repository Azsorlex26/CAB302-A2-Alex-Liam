package systemObjects;

public class Store {

	private String name;
	private Stock inventory;
	//private *something* capital; //I don't know what this is supposed to be yet. Will ask about it later.
	
	public Store(String name) {
		this.name = name;
		this.inventory = new Stock();
		//capital declaration here maybe
	}

	public String getName() {
		return name;
	}
	
}
