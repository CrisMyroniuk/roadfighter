package roadfighter.objects;

import java.util.ArrayList;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import roadfighter.utils.GameObject;

public class Player extends GameObject {

	private PlayerState state;
	private CarPlayer car;
	private ArrayList<KeyCode> keys;

	public Player(CarPlayer carPlayer) {
		setKeys(new ArrayList<KeyCode>());
		keys.add(KeyCode.W);
		keys.add(KeyCode.A);
		keys.add(KeyCode.S);
		keys.add(KeyCode.D);
		car = carPlayer;
	}

	public Player(CarPlayer carPlayer,ArrayList<KeyCode> kc) {
		car = carPlayer;
		setKeys(kc);
	}

	public ArrayList<KeyCode> getKeys() {
		return keys;
	}

	public void setKeys(ArrayList<KeyCode> keys) {
		this.keys = keys;
	}

	public Integer getPoint() {
		return car.getPoint();
	}

	public CarPlayer getCarPlayer() {
		return car;
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
	
	public boolean hasPickedUpPoints() {
		return this.car.hasPickedUpPoints();
	}
	
	public Integer readPoints() {
		return this.car.readPoints();
	}

	@Override
	public void destroy() {}

	public boolean isAlive() {
		return car.isAlive();
	}

	// endregion
}
