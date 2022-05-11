package roadfighter;

public class CarPlayer extends Vehicle{
	
	//region Variables
	private double aceleration;
	private Integer point;
	private double maximusSpeed ;
	//private Enum direction ;
	private boolean turbo ;	
	private Movement movement;
	//endregion
	
	//region Properties
	public double getAceleration() {
		return aceleration;
	}
	public void setAceleration(double aceleration) {
		this.aceleration = aceleration;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public double getMaximusSpeed() {
		return maximusSpeed;
	}
	public void setMaximusSpeed(double maximusSpeed) {
		this.maximusSpeed = maximusSpeed;
	}
	//public Enum getDirection() {
	//	return direction;
	//}
	//public void setDirection(Enum direction) {
	//	this.direction = direction;
	//}
	public boolean isTurbo() {
		return turbo;
	}
	public void setTurbo(boolean turbo) {
		this.turbo = turbo;
	}
	public Movement getMovement() {
		return movement;
	}
	public void setMovement(Movement movement) {
		this.movement = movement;
	}
	//endregion
	
	//region Constructor
	
	//endregion
	
	//region Metodos
	
		public void addPoints(int p) {
			setPoint(getPoint()+p);
		}
		
		public void removePoints(int p) {
			setPoint(getPoint()-p);
		}
		
		public void changeSpeed(double sp,Action ac) {
			switch (ac) {
			case SPEED_UP:
				//a desarrollar
				break;
			case SPEED_DOWN:
				setSpeed(getSpeed()-sp);
				//a desarrollar
				break;
			case STOP:
				setSpeed(0);
				break;
			}
			
		}
	
	//endregion
	
}
