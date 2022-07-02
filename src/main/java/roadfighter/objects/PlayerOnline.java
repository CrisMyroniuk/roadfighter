package roadfighter.objects;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import roadfighter.client.utils.Message;
import roadfighter.client.utils.MessageType;
import roadfighter.threads.ListenerThread;
import roadfighter.utils.GameObject;

public class PlayerOnline extends GameObject {

	private PlayerState state;
	private CarPlayer car;
	private ArrayList<KeyCode> keys;
	private ListenerThread servidor;
	private DataOutputStream output;

	public PlayerOnline(CarPlayer carPlayer, DataOutputStream output) {
		setKeys(new ArrayList<KeyCode>());
		keys.add(KeyCode.W);
		keys.add(KeyCode.A);
		keys.add(KeyCode.S);
		keys.add(KeyCode.D);
		car = carPlayer;
//		this.servidor = servidor;
		this.output = output;
	}

	public PlayerOnline(CarPlayer carPlayer,ArrayList<KeyCode> kc) {
		car = carPlayer;
		setKeys(kc);

		this.output = output;
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
			output.writeUTF(new Message(MessageType.PLAYER_MOVE, "up").getJsonString());
			//car.setDirectionUp();
		}
		if (e.getCode() == getKeys().get(1)) {
			output.writeUTF(new Message(MessageType.PLAYER_MOVE, "left").getJsonString());
			//car.setDirectionLeft();
		}
		if (e.getCode() == getKeys().get(2)) {
			output.writeUTF(new Message(MessageType.PLAYER_MOVE, "down").getJsonString());
			//car.setDirectionDown();
		}
		if (e.getCode() == getKeys().get(3)) {
			output.writeUTF(new Message(MessageType.PLAYER_MOVE, "right").getJsonString());
			//car.setDirectionRight();
		}
	}
	
	public void eventReleased(KeyEvent e) throws IOException {
		if (e.getCode() == getKeys().get(0)) {
			output.writeUTF(new Message(MessageType.PLAYER_STOP, "up").getJsonString());
			//car.setDirectionNone(Direction.UP);
		}
		if (e.getCode() == getKeys().get(1)) {
			output.writeUTF(new Message(MessageType.PLAYER_STOP, "left").getJsonString());
			//car.setDirectionNone(Direction.LEFT);
		}
		if (e.getCode() == getKeys().get(2)) {
			output.writeUTF(new Message(MessageType.PLAYER_STOP, "down").getJsonString());
			//car.setDirectionNone(Direction.DOWN);
		}
		if (e.getCode() == getKeys().get(3)) {
			output.writeUTF(new Message(MessageType.PLAYER_STOP, "right").getJsonString());
			//car.setDirectionNone(Direction.RIGHT);
		}
	}
	
	public void eventPressed(KeyCode e) {
		if (e == getKeys().get(0)) {
			car.setDirectionUp();
			System.out.println("arriiba");
		}
		else if (e == getKeys().get(1)) {
			car.setDirectionLeft();
		}
		else if (e == getKeys().get(2)) {
			car.setDirectionDown();
		}
		else if (e == getKeys().get(3)) {
			car.setDirectionRight();
		}
	}
	
	public void eventReleased(KeyCode e) {
		if (e == getKeys().get(0)) {
			car.setDirectionNone(Direction.UP);
		}
		else if (e == getKeys().get(1)) {
			car.setDirectionNone(Direction.LEFT);
		}
		else if (e == getKeys().get(2)) {
			car.setDirectionNone(Direction.DOWN);
		}
		else if (e == getKeys().get(3)) {
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