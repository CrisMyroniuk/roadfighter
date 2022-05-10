package roadfighter;

public abstract class Vehicle extends MapObject {
	private double speed;

	public double getSpeed() {
		System.out.println("Error");
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
}
