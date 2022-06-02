package roadfighter.objects;

import roadfighter.utils.GameObject;

public class Player extends GameObject{

	private Integer points;
	private PlayerState state;
	private CarPlayer car;
	
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;

	public Player () {
		car = new CarPlayer(0, 0, this);
		state=state.PLAYER_LIVE;
		points=0;
		
		up = false;
		down = false;
		left = false;
		right = false;
	}
	
	public Integer getPoint() {
		return points;
	}
	
	public CarPlayer getCarPlayer() {
		return car;
	}

	public void setPoint(Integer point) {
		this.points = point;
	}
	
	public void addPoints(int p) {
		setPoint(getPoint() + p);
	}

	public void removePoints(int p) {
		setPoint(getPoint() - p);
	}
	
	public void input(Direction direction, boolean pressed) {
		switch (direction) {
		case UP:
			up = pressed;
			break;
		case LEFT:
			left = pressed;
			break;
		case DOWN:
			down = pressed;
			break;
		case RIGHT:
			right = pressed;
			break;
		default:
			break;
		}
	}
	
	public boolean upPressed() {
		return up;
	}
	
	public boolean leftPressed() {
		return left;
	}
	
	public boolean downPressed() {
		return down;
	}
	
	public boolean rightPressed() {
		return right;
	}
	
	//region Metodos
	
//	public void setDireccion(Direction d) {
//		switch (d) {
//		case RIGHT: {
//			car.setDirectionRight();
//			break;
//		}
//		case LEFT: {
//			car.setDirectionLeft();
//			break;
//		}
//		case UP: {
//			car.setDirectionUp();
//			car.changeSpeed(10, Action.SPEED_UP);
//			break;
//		}
//		case DOWN: {
//			car.setDirectionDown();
//			car.changeSpeed(10, Action.SPEED_DOWN);
//			break;
//		}
//		default:
//			throw new IllegalArgumentException("Unexpected value: " + d);
//		}
//	}
	
	public Coordinate myCoord() {
		return car.getCoordinate();
	}
	
	public void changeStateDeath() {
		
		this.state = PlayerState.PLAYER_DEATH;
		
	}
	
	public void changeStateWin() {
		
		this.state = PlayerState.PLAYER_WINS;
		
	}
	
	public PlayerState getState() {
		return state;
	}
	
	public CarPlayer newCar(double x, double y) {
		this.car = new CarPlayer(x, y, this);
		return car;
	}

	@Override
	public void destroy() {
		// TODO lo que sea que se tenga que desalocar del jugador
		// supongo que eventualmente podria ir si se cierra alguna conexion
		
	}
	
	//endregion
	
	/*private void addInputEvents() {
		currentScene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				switch (e.getCode()) {
				case RIGHT:
				case D:
					mario.setDirectionRight(true);
					break;
				case LEFT:
				case A:
					mario.setDirectionLeft(true);
					break;
				case Q:
					mario.die();
					break;
				default:
					break;
				}
			}
		});*/
	
}
