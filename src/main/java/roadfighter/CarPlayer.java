package roadfighter;

public class CarPlayer extends Vehicle {

	// region Variables
	private double aceleration;
	private Integer point;
	private double speedLimit;
	private Direction direction ;
	

	private boolean turbo;
	// private Movement movement;
	private static double turboDuration = 100;
	private static double turboExtraSpeed = 50; // +50
	private static double turboExtraAceleration = 2; // x2
	private static double turboSpeedLimit = 100; // +100
	// endregion

	// region Properties
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

	public double getSpeedLimit() {
		return speedLimit;
	}

	public void setSpeedLimit(double maximusSpeed) {
		this.speedLimit = maximusSpeed;
	}
	
	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	// public Enum getDirection() {
	// return direction;
	// }
	// public void setDirection(Enum direction) {
	// this.direction = direction;
	// }
	public boolean isTurbo() {
		return turbo;
	}

	public void setTurbo(boolean turbo) {
		this.turbo = turbo;
	}
	// public Movement getMovement() {
	// return movement;
	// }
	// public void setMovement(Movement movement) {
	// this.movement = movement;
	// }
	// endregion

	// region Constructor

	public CarPlayer(double x, double y) {

		this.aceleration = 20;
		this.speedLimit = 200;
		this.turbo = false;
		this.point = 0;
		setCoordinate(new Coordinate(x, y));
	}

	// endregion

	// region Metodos

	public void addPoints(int p) {
		setPoint(getPoint() + p);
	}

	public void removePoints(int p) {
		setPoint(getPoint() - p);
	}

	public void changeSpeed(double sp, Action ac) {
		switch (ac) {
		case SPEED_UP:
			if(getSpeed()<getSpeedLimit())
				setSpeed(getSpeed() + sp);
			// a desarrollar
			break;
		case SPEED_DOWN:
			if(getSpeed() < sp)
				sp = getSpeed();	//Para que la velocidad no sea menor que cero
			setSpeed(getSpeed() - sp);
			// a desarrollar
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
		if (!isTurbo()) {
			this.aceleration *= turboExtraAceleration;
			this.speedLimit += turboSpeedLimit;
			changeSpeed(turboExtraSpeed, Action.SPEED_UP);
			this.turbo = true;
		}
	}

	public void desactivateTurbo() {
		this.aceleration /= turboExtraAceleration;
		changeSpeed(turboExtraSpeed, Action.SPEED_DOWN);
		this.speedLimit -= turboSpeedLimit;
		this.turbo = false;
	}

	public void setDirectionRight() {
		setDirection(Direction.RIGHT);
		move(10,0);
	}

	public void setDirectionLeft() {
		setDirection(Direction.LEFT);
		move(-10,0);
	}

	public void setDirectionUp() {
		setDirection(Direction.UP);
		move(0,10);
	}

	public void setDirectionDown() {
		setDirection(Direction.DOWN);
		move(0,-10);
	}

	@Override
	public void move(double x, double y) {
		// TODO Auto-generated method stub.
		getCoordinate().setX(getCoordinate().getX()+x);
		getCoordinate().setY(getCoordinate().getY()+y);
	}

	// endregion

}
