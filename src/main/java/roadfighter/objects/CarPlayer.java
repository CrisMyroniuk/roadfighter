package roadfighter.objects;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import roadfighter.interfaces.Renderable;

public class CarPlayer extends Vehicle implements Renderable{

	// region Variables
	private double acceleration;
	private Integer point;
	private double speedLimit;
	private Direction direction ;
	private Turbo turbo;
	
	private final int width = 84;
	private final int height = 134;
	private ImageView render;
	private Image sprite;

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

	public void setDirection(Direction direction) {
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
		this.direction = Direction.UP;
		this.setSpeed(250);
		
		initImages();
		render = new ImageView(sprite);
		render.relocate(getCoordinate().getX() - width / 2, 
						getCoordinate().getY() - height / 2);
	}
	
	private void initImages() {
		sprite = new Image("file:src/resources/images/CarPlayerSprite.png");
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

	public void setDirectionRight() {
		setDirection(Direction.RIGHT);
		//move(10,0);
	}

	public void setDirectionLeft() {
		setDirection(Direction.LEFT);
		//move(-10,0);
	}

	public void setDirectionUp() {
		setDirection(Direction.UP);
		//move(0,10);
	}

	public void setDirectionDown() {
		setDirection(Direction.DOWN);
		//move(0,-10);
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
		if (direction.equals(Direction.RIGHT)) 
			translateX = 200;
		else if (direction.equals(Direction.LEFT))
			translateX = -200;
		
		translateY = -this.getSpeed();
		
		move(translateX * delta, translateY * delta);
		
		render.setTranslateX(getCoordinate().getX() - width / 2);
		render.setTranslateY(getCoordinate().getY() - height / 2);
	}

	@Override
	public Node getRender() {
		return render;
	}
}
