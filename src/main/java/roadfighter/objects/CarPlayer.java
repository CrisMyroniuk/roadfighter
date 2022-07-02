package roadfighter.objects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import roadfighter.Config;
import roadfighter.interfaces.Collidator;
import roadfighter.interfaces.Collideable;

public class CarPlayer extends Vehicle implements Collidator{

	// region Variables
	private double acceleration;
	private double speedLimit;
	private Turbo turbo;
	
	private Direction direction;
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	
	private final int WIDTH = 70;
	private final int HEIGHT = 160;
	private final double XMAX = 1056.00;
	private final double XMIN = 447.00;
	private Rectangle hitbox;

	private boolean control = true;
	private double controlOffTimer;
	private final double controlOffTimerMax = 0.75;
	private double controlLossSpeed;
	private double controlLossX;
	private double controlLossY;
	
	//private boolean alive = true;
	private Integer point;
	private boolean pickedUpPoints = false;
	
	private PlayerState playerState;
	private double crashTimer = 0;
	private double crashTimerMax = 0.25;
	
	private int cantLifes;
	
	
	public int getCantLifes() {
		return cantLifes;
	}

	public void setCantLifes(int cantLifes) {
		this.cantLifes = cantLifes;
	}

	public PlayerState getPlayerState() {
		return playerState;
	}

	// region Properties
	
	public double getAceleration() {
		return acceleration;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public void setAceleration(double aceleration) {
		this.acceleration = aceleration;
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

	private void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public Turbo getTurbo() {
		return turbo;
	}

	// region Constructor

	public CarPlayer(double x, double y) {

		this.acceleration = 20;
		this.speedLimit = 200;
		this.turbo = new Turbo(false); //inicializamos el turbo en desactivado.
		this.point = 0;
		setCoordinate(new Coordinate(x, y));
		setOriginalCoordinate(new Coordinate(x, y));
		this.direction = Direction.UP;
		this.setSpeed(Config.carVerticalSpeed);
		this.setHorizontalSpeed(Config.carHorizontalSpeed);
		
		
		hitbox = new Rectangle(x - WIDTH / 2, y - HEIGHT / 2, WIDTH, HEIGHT);
		hitbox.setFill(null);
		hitbox.setStroke(Color.FUCHSIA);
		
		controlOffTimer = controlOffTimerMax;
		controlLossSpeed = getSpeed() / 3;
		
		playerState = PlayerState.PLAYER_LIVE;
		cantLifes = 3;
	}
	
	public void changeStateDeath() {
		cantLifes--;
		if(cantLifes == 0) {
			playerState = PlayerState.PLAYER_DEATH;
		}
	}
	// endregion

	// region Metodos

	public void addPoints(int p) {
		setPoint(getPoint() + p);
		pickedUpPoints = true;
	}

	public void removePoints(int p) {
		setPoint(getPoint() - p);
		pickedUpPoints = true;
	}
	
	public boolean canCrash() {
		return crashTimer <= 0;
	}
	
	public boolean hasPickedUpPoints() {
		return pickedUpPoints;
	}
	
	public int readPoints() {
		pickedUpPoints = false;
		return point;
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

	public void setDirectionNone(Direction d) {
		switch(d) {
		case DOWN:
			setDown(false);
			break;
		case LEFT:
			setLeft(false);
			break;
		case RIGHT:
			setRight(false);
			break;
		case UP:
			setUp(false);
			break;
		default:
			break;
		
		}
	}
	
	public void setDirectionRight() {
		setDirection(Direction.RIGHT);
		setRight(true);
	}

	public void setDirectionLeft() {
		setDirection(Direction.LEFT);
		setLeft(true);
	}

	public void setDirectionUp() {
		setDirection(Direction.UP);
		setUp(true);
	}

	public void setDirectionDown() {
		setDirection(Direction.DOWN);
		setDown(true);
	}
	
	public void activateTurbo() {
		turbo.activateTurbo(this);
	}
	
	public void desactivateTurbo() {
		turbo.desactivateTurbo(this);
	}
	
	public void updateTurboTime(double dt) {
		if (turbo.getDuration() - dt <= 0) {
			desactivateTurbo();
		}
	}

	@Override
	public void move(double x, double y) {
		Coordinate actualCoordinate = getCoordinate();
		double actualX = actualCoordinate.getX();
		if(x < 0) {
			if(actualX > XMIN) {
				getCoordinate().setX(getCoordinate().getX()+x);
			}
		}else {
			if(actualX < XMAX) {
				getCoordinate().setX(getCoordinate().getX()+x);
			}
		}
		//getCoordinate().setX(getCoordinate().getX()+x);
		getCoordinate().setY(getCoordinate().getY()+y);
	}

	// endregion
	
	public void update(double delta) {
		double translateX = 0;
		double translateY = 0;
		
		if (crashTimer > 0)
			crashTimer -= delta;
		else
			crashTimer = crashTimerMax;
			
		
		if (control) {
			if (!isLeft() || !isRight()) {
				if (isLeft()) {
					translateX = -horizontalSpeed;
				} else if (isRight()) {
					translateX = horizontalSpeed;
				}
			}
			if (!isUp()|| !isDown()) {
				if (isUp()) {
					translateY = -getSpeed();
				} else if (isDown()) {
					translateY = getSpeed();
				}
			}
			
			move(translateX * delta, translateY * delta);
			
		}
		else {
			if (controlOffTimer <= 0) {
				control = true;
			}
			else {
				controlOffTimer -= delta;
				move(controlLossSpeed * controlLossX * delta, controlLossSpeed * controlLossY * delta);
			}
		}
		hitbox.setX(this.getCoordinate().getX() - WIDTH / 2);
		hitbox.setY(this.getCoordinate().getY() - HEIGHT / 2);
	}

	@Override
	public Shape getCollider() {
		return hitbox;
	}

	@Override
	public void collide(Collideable collideable) {
		collideable.effectPlayer(this);
	}

	@Override
	public void effectPlayer(CarPlayer other) {
		
		this.loseControl(Coordinate.calculateDirection(other.getCoordinate(), this.getCoordinate()).normalize());
	}
	
	public void loseControl(Coordinate direction) {
		controlLossX = direction.getX();
		controlLossY = direction.getY();
		control = false;
		controlOffTimer = controlOffTimerMax;
	}
	
	public double getHorizontalSpeed() {
		return horizontalSpeed;
	}

	@Override
	public void effectEnemy(GoodDriver source) {
		
	}
	
	public boolean isAlive() {
		return playerState == PlayerState.PLAYER_LIVE;
	}

}
