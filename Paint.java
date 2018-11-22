package paintWizard;

public class Paint {
	
	private String name;
	private double price;
	private double volume;
	private double coverage;
	private double waste;
	private double costForRoom;

	public Paint (String name, double price, double volume, double coverage) {
		this.name = name;
		this.price = price;
		this.volume = volume;
		this.coverage = coverage * volume;
	}
	
	public String getName() {
		return name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public double getVolume() {
		return volume;
	}

	public double getCoverage() {
		return coverage;
	}
	
	public double getWaste() {
		return waste;
	}
	
	public void setWaste(double waste) {
		this.waste = waste;
	}
	
	public double getCostForRoom() {
		return costForRoom;
	}
	
	public void setCostForRoom(double costForRoom) {
		this.costForRoom = costForRoom;
	}
	
	
	public String toString() {
		return "Name: " + name + ", Price: " + price + ", Volume: " + volume + ", Coverage (per Paint Tin): " + coverage + "m2";
	}
}
	