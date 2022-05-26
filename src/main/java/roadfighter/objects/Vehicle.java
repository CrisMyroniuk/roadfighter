package roadfighter.objects;

public abstract class Vehicle extends MapObject {
	private double speed;

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public abstract void move(double x, double y);
	
	
}
