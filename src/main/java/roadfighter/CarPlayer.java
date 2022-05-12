package roadfighter;

public class CarPlayer extends Vehicle{
	
	//region Variables
	private double aceleration;
	private Integer point;
	private double maximusSpeed ;
	//private Enum direction ;
	private boolean turbo ;	
	private Movement movement;
	private static double turboDuration = 100;
	private static double turboExtraSpeed = 50; //+50
	private static double turboExtraAceleration = 2; //x2
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
	
	public CarPlayer(double x, double y,double h, double w, double speed) {
		
		this.aceleration = 20;
		this.maximusSpeed = 200;
		this.turbo = false;
		this.point = 0;
		setX(x);
		setY(y);
		setHeight(h);
		setWidth(w);
		setSpeed(speed);
	}
	
	//endregion
	
	//region Metodos
	
		public void addPoints(int p) {
			setPoint(getPoint()+p);
		}
		
		
		public void removePoints(int p) {
			setPoint(getPoint()-p);
		}
		
		public void changeSpeed(double sp, Action ac) {
			switch (ac) {
			case SPEED_UP:
				setSpeed(getSpeed()+sp);
				setMaximusSpeed(getMaximusSpeed() + sp);
				//a desarrollar
				break;
			case SPEED_DOWN:
				setSpeed(getSpeed()-sp);
				setMaximusSpeed(getMaximusSpeed() - sp);
				//a desarrollar
				break;
			case STOP:
				setSpeed(0);
				break;
			}
			
		}
		
		public void updateTurboTime(double dt) {
			if (turboDuration - dt <= 0) {
				desactivateTurbo();
			}
		}
		
		public void activateTurbo() {
			if(!isTurbo()) {
				this.aceleration *= turboExtraAceleration;
				changeSpeed(turboExtraSpeed, Action.SPEED_UP);
				this.turbo = true;
			}
		}
		
		public void desactivateTurbo() {
			this.aceleration /= turboExtraAceleration;
			changeSpeed(turboExtraSpeed, Action.SPEED_DOWN);
			this.turbo = false;
		}
	
	//endregion
	
}
