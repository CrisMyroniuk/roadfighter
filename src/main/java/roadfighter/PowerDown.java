package roadfighter;

public class PowerDown extends Item{

	private double speedDown;
	
	public double getSpeedDown() {
		return speedDown;
	}

	public void setSpeedDown(double speedDown) {
		this.speedDown = speedDown;
	}
	
	public PowerDown(int x, int y,double h, double w, double sp) {
		setCoordinate(new Coordinate(x, y));
		setHeight(h);
		setWidth(w);
		setModel(null);
		setSpeedDown(sp);
		visible=true;
		
	}

	@Override
	public void effectPlayer(CarPlayer cp) {
		// Baja la velocidad del auto
		cp.changeSpeed(10,Action.SPEED_DOWN);
	}

	

}
