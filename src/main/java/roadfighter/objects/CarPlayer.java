package roadfighter.objects;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import roadfighter.Config;
import roadfighter.interfaces.Collidator;
import roadfighter.interfaces.Collideable;
import roadfighter.interfaces.Renderable;
import roadfighter.utils.AudioResources;

public class CarPlayer extends Vehicle implements Collidator, Renderable{

	// region Variables
	private double acceleration;
	private double speedLimit;
	private Turbo turbo;
	
	private Direction direction;
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	
	private ImageView render;
	private Image sprite;
	
	private final int WIDTH = 80;
	private final int HEIGHT = 140;
	private Rectangle hitbox;

	private boolean control = true;
	private double controlOffTimer;
	private final double controlOffTimerMax = 0.75;
	private double controlLossSpeed;
	private double controlLossX;
	private double controlLossY;
	
	private boolean alive = true;
	private Integer point;
	private boolean pickedUpPoints = false;
	
	private AudioClip explosionAudio;
	private AudioClip coinAudio;
	/*private boolean turbo;
	private static double turboDuration = 100;
	private static double turboExtraSpeed = 50; // +50
	private static double turboExtraAceleration = 2; // x2
	private static double turboSpeedLimit = 100; // +100
	*/
	// endregion

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

	// public Enum getDirection() {
	// return direction;
	// }
	// public void setDirection(Enum direction) {
	// this.direction = direction;
	// }
	// public Movement getMovement() {
	// return movement;
	// }
	// public void setMovement(Movement movement) {
	// this.movement = movement;
	// }
	// endregion

	// region Constructor

	public CarPlayer(double x, double y) {

		
		this.acceleration = 20;
		this.speedLimit = 200;
		this.turbo = new Turbo(false); //inicializamos el turbo en desactivado.
		this.point = 0;
		setCoordinate(new Coordinate(x, y));
		setOriginalCoordinate(new Coordinate(x, y));
		this.direction = Direction.UP;
		this.setSpeed(250);
		this.setHorizontalSpeed(100);
		
		initImages();
		initAudios();
		render = new ImageView(sprite);
		//render.relocate(x - WIDTH / 2, y - HEIGHT / 2);
		
		hitbox = new Rectangle(x - WIDTH / 2, y - HEIGHT / 2, WIDTH, HEIGHT);
		hitbox.setFill(null);
		hitbox.setStroke(Color.FUCHSIA);
		
		controlOffTimer = controlOffTimerMax;
		controlLossSpeed = getSpeed() / 3;
	}
	
	private void initImages() {
		sprite = new Image("file:src/resources/images/Player.png", WIDTH, HEIGHT, false, false);
	}

	private void initAudios() {
		explosionAudio = AudioResources.getExplosionAudio();
		explosionAudio.setVolume(Config.masterVolumeModifier * Config.effectsVolumeModifier);

		coinAudio =  AudioResources.getCoinAudio();
		coinAudio.setVolume(Config.masterVolumeModifier * Config.effectsVolumeModifier);
	}
	// endregion

	// region Metodos

	public AudioClip getExplosionAudio() {
		return explosionAudio;
	}
	
	public AudioClip getCoinAudio() {
		return coinAudio;
	}

	public void addPoints(int p) {
		setPoint(getPoint() + p);
		pickedUpPoints = true;
	}

	public void removePoints(int p) {
		setPoint(getPoint() - p);
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
		getCoordinate().setX(getCoordinate().getX()+x);
		getCoordinate().setY(getCoordinate().getY()+y);
	}

	// endregion
	
	public void update(double delta) {
		double translateX = 0;
		double translateY = 0;
		
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
		render.setTranslateX(getCoordinate().getX() - WIDTH / 2);
		render.setTranslateY(getCoordinate().getY() - HEIGHT / 2);
		hitbox.setX(this.getCoordinate().getX() - WIDTH / 2);
		hitbox.setY(this.getCoordinate().getY() - HEIGHT / 2);
	}

	@Override
	public Node getRender() {
		return render;
	}

	@Override
	public Shape getCollider() {
		return hitbox;
	}

	@Override
	public void collide(Collideable collideable) {
		System.out.println("colision");

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
	
	public void die() {
		alive = false;
	}
	
	public boolean isAlive() {
		return alive;
	}

}
