package roadfighter.objects;

import roadfighter.interfaces.Updatable;

public abstract class Vehicle extends MapObject implements Updatable {
	private double speed;

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public abstract void move(double x, double y);
	
	
}
