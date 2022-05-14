package roadfighter;

public class Turbo {
	private boolean activate;
	private static double duration = 100;
	//private static double extraSpeedCar = 50; // +50
	private static double extraAccelerationCar = 2; // x2
	private static double incrementSpeedLimitCar = 100; // +100
	
	public Turbo(boolean activate) {
		this.activate = activate;
	}
	
	public boolean isTurbo() {
		return this.activate;
	}

	public void setTurbo(boolean turbo) {
		this.activate = activate;
	}
	
	public double getDuration() {
		return this.duration;
	}
	
	public void activateTurbo(CarPlayer cp) {
		if (!isTurbo()) {
			cp.setAceleration(cp.getAceleration() * extraAccelerationCar);
			cp.setSpeedLimit(cp.getSpeedLimit() + incrementSpeedLimitCar);
			//cp.changeSpeed(extraSpeedCar, Action.SPEED_UP);
			this.activate = true;
		}
	}

	public void desactivateTurbo(CarPlayer cp) {
		cp.setAceleration(cp.getAceleration() / extraAccelerationCar);
		//cp.changeSpeed(extraSpeedCar, Action.SPEED_DOWN);
		cp.setSpeedLimit(cp.getSpeedLimit() - incrementSpeedLimitCar);
		this.activate = false;
	}
	
	

}
