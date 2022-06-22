package roadfighter.objects;

import roadfighter.interfaces.Collideable;
import roadfighter.interfaces.Updatable;

public abstract class Vehicle extends MapObject implements Updatable, Collideable {
	private double speed;
	protected double horizontalSpeed;

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public void setHorizontalSpeed(double horizontalSpeed) {
		this.horizontalSpeed = horizontalSpeed;
	}
	
	public abstract void move(double x, double y);
	
	public abstract void effectPlayer(CarPlayer cp);
}
