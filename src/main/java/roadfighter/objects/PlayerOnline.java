package roadfighter.objects;

import java.awt.TrayIcon.MessageType;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import roadfighter.threads.ListenerThread;
import roadfighter.utils.GameObject;

public class PlayerOnline extends GameObject {

	private PlayerState state;
	private CarPlayer car;
	private ArrayList<KeyCode> keys;
	private ListenerThread servidor;
	private DataOutputStream output;

	public PlayerOnline(CarPlayer carPlayer, ListenerThread servidor) {
		setKeys(new ArrayList<KeyCode>());
		keys.add(KeyCode.W);
		keys.add(KeyCode.A);
		keys.add(KeyCode.S);
		keys.add(KeyCode.D);
		car = carPlayer;
		this.servidor = servidor;
		
	}

	public PlayerOnline(CarPlayer carPlayer,ArrayList<KeyCode> kc) {
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

	public void eventPressed(KeyEvent e) throws IOException {
		if (e.getCode() == getKeys().get(0)) {
			output.writeUTF(MessageType.UP);
			//car.setDirectionUp();
		}
		if (e.getCode() == getKeys().get(1)) {
			output.writeUTF(MessageType.LEFT);
			//car.setDirectionLeft();
		}
		if (e.getCode() == getKeys().get(2)) {
			output.writeUTF(MessageType.DOWN);
			//car.setDirectionDown();
		}
		if (e.getCode() == getKeys().get(3)) {
			output.writeUTF(MessageType.RIGHT);
			//car.setDirectionRight();
		}
	}
	
	public void eventReleased(KeyEvent e) throws IOException {
		if (e.getCode() == getKeys().get(0)) {
			output.writeUTF(MessageType.NO_UP);
			//car.setDirectionNone(Direction.UP);
		}
		if (e.getCode() == getKeys().get(1)) {
			output.writeUTF(MessageType.NO_LEFT);
			//car.setDirectionNone(Direction.LEFT);
		}
		if (e.getCode() == getKeys().get(2)) {
			output.writeUTF(MessageType.NO_DOWN);
			//car.setDirectionNone(Direction.DOWN);
		}
		if (e.getCode() == getKeys().get(3)) {
			output.writeUTF(MessageType.NO_RIGHT);
			//car.setDirectionNone(Direction.RIGHT);
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