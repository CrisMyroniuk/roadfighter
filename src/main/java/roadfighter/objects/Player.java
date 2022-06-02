package roadfighter.objects;

import java.util.ArrayList;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import roadfighter.utils.GameObject;

public class Player extends GameObject {

	private Integer points;
	private PlayerState state;
	private CarPlayer car;
	private ArrayList<KeyCode> keys;
//	private boolean up;
//	private boolean down;
//	private boolean left;
//	private boolean right;

	public Player(CarPlayer carPlayer) {
		setKeys(new ArrayList<KeyCode>());
		keys.add(KeyCode.W);
		keys.add(KeyCode.A);
		keys.add(KeyCode.S);
		keys.add(KeyCode.D);
		car = carPlayer;
		state = state.PLAYER_LIVE;
		points = 0;
	}

	public Player(CarPlayer carPlayer,ArrayList<KeyCode> kc) {
		car = carPlayer;
		setKeys(kc);
		state = state.PLAYER_LIVE;
		points = 0;
	}

	public ArrayList<KeyCode> getKeys() {
		return keys;
	}

	public void setKeys(ArrayList<KeyCode> keys) {
		this.keys = keys;
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

	public void eventPressed(KeyEvent e) {
		if (e.getCode() == getKeys().get(0)) {
			car.setDirectionUp();
		}
		if (e.getCode() == getKeys().get(1)) {
			car.setDirectionLeft();
		}
		if (e.getCode() == getKeys().get(2)) {
			car.setDirectionDown();
		}
		if (e.getCode() == getKeys().get(3)) {
			car.setDirectionRight();
		}
	}
	
	public void eventReleased(KeyEvent e) {
		if (e.getCode() == getKeys().get(0)) {
			car.setDirectionNone(Direction.UP);
		}
		if (e.getCode() == getKeys().get(1)) {
			car.setDirectionNone(Direction.LEFT);
		}
		if (e.getCode() == getKeys().get(2)) {
			car.setDirectionNone(Direction.DOWN);
		}
		if (e.getCode() == getKeys().get(3)) {
			car.setDirectionNone(Direction.RIGHT);
		}
	}

//	public void input(Direction direction, boolean pressed) {
//		switch (direction) {
//		case UP:
//			car.up = pressed;
//			break;
//		case LEFT:
//			left = pressed;
//			break;
//		case DOWN:
//			down = pressed;
//			break;
//		case RIGHT:
//			right = pressed;
//			break;
//		default:
//			break;
//		}
//	}

//	public boolean upPressed() {
//		return up;
//	}
//	
//	public boolean leftPressed() {
//		return left;
//	}
//	
//	public boolean downPressed() {
//		return down;
//	}
//	
//	public boolean rightPressed() {
//		return right;
//	}
//	
	// region Metodos

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


	@Override
	public void destroy() {
		// TODO lo que sea que se tenga que desalocar del jugador
		// supongo que eventualmente podria ir si se cierra alguna conexion

	}

	// endregion

	/*
	 * private void addInputEvents() {
	 * currentScene.addEventHandler(KeyEvent.KEY_PRESSED, new
	 * EventHandler<KeyEvent>() {
	 * 
	 * @Override public void handle(KeyEvent e) { switch (e.getCode()) { case RIGHT:
	 * case D: mario.setDirectionRight(true); break; case LEFT: case A:
	 * mario.setDirectionLeft(true); break; case Q: mario.die(); break; default:
	 * break; } } });
	 */

}
