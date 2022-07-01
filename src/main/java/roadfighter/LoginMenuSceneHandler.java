package roadfighter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import com.google.gson.Gson;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import roadfighter.client.utils.Message;
import roadfighter.client.utils.MessageType;
import roadfighter.objects.Background;
import roadfighter.objects_menu.ButtonMenu;
import roadfighter.objects_menu.MenuTextField;
import roadfighter.objects_menu.Title;
import roadfighter.utils.GameObject;
import roadfighter.utils.GameObjectBuilder;

public class LoginMenuSceneHandler extends SceneHandler {
	private Group rootGroup;
	private Background background;
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	private Title title;
	private GameObjectBuilder gameOB;
	
	private MenuTextField ipAdress;
	private MenuTextField port;
	private MenuTextField userName;
	private ButtonMenu login;
	private ButtonMenu back;

	private EventHandler<ActionEvent> onPressHandlerLogin;
	private EventHandler<ActionEvent> onPressHandlerBack;
	
	public LoginMenuSceneHandler(RoadFighterGame g) {
		super(g);
	}

	protected void prepareScene() {
		rootGroup = new Group();
		scene = new Scene(rootGroup, Config.baseWidth, Config.baseHeight, Color.BLACK);
	}

	protected void defineEventHandlers() {
		mouseEventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
//				if (event.getButton() == MouseButton.PRIMARY) {
//					g.startGame();
//				}
			}
		};

		keyEventHandler = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				switch (e.getCode()) {
				case ESCAPE:
					g.startMenu();
					break;
				default:
					break;
				}
			}
		};
		
		onPressHandlerLogin = new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) {
				tryToConnect();
			}
		};
		
		onPressHandlerBack = new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) {
				g.startMenu();
			}
		};
	}
	
	private void tryToConnect() {
		Socket socket;
		DataInputStream input;
		DataOutputStream output;
		try {
			socket = new Socket(ipAdress.getText(), Integer.parseInt(port.getText()));
			input = new DataInputStream(socket.getInputStream());
			output = new DataOutputStream(socket.getOutputStream());
			socket.setSoTimeout(5000);
			output.writeUTF(new Message(MessageType.SESSION_LOGIN, userName.getText()).getJsonString());
			String response = input.readUTF();
			Message messageResponse = new Gson().fromJson(response, Message.class);
			if (messageResponse.getType().equals(MessageType.SESSION_LOGIN) && messageResponse.getContent().equals("logedIn")) {
				socket.setSoTimeout(0);
				g.startOnlineMenu(input, output);
			} else {
				throw new Exception();
			}
		} catch (NumberFormatException e) {
			userName.setText("ip o puerto invalido");
			System.err.println("ip o puerto invalido");
		} catch (SocketTimeoutException e) {
			userName.setText("time out");
			System.err.println("time out");
		} catch (IOException e) {
			userName.setText("fallo la conexion");
			System.err.println("no se pudo conectar");
		} catch (Exception e) {
			userName.setText("nombre de usuario en uso");
			System.err.println("nombre de usuario en uso");
		}
	}

	public void load() {
		Group baseGroup = new Group();
		rootGroup.getChildren().add(baseGroup);
		
		gameOB = GameObjectBuilder.getInstance();
		gameOB.setRootNode(baseGroup);
		
		background = new Background();

		title = new Title();
		
		ipAdress = new MenuTextField("ip", 500, Config.baseHeight * 3 / 5);
		port = new MenuTextField("port", 500, Config.baseHeight * 3 / 5 + 50);
		userName = new MenuTextField("UserName", 500, Config.baseHeight * 3 / 5 + 100);
		login = new ButtonMenu("LOGIN", Config.baseHeight * 3 / 5 + 200);
		back = new ButtonMenu("BACK", Config.baseHeight * 3 / 5 + 300);
		
		gameOB.add(ipAdress, port, userName, login, back);
		
		gameObjects.add(background);
		gameObjects.add(title);
		gameOB.add(gameObjects);

		addTimeEventsAnimationTimer();
		addInputEvents();
	}
	
	@Override
	public void update(double delta) {
		super.update(delta);
	}

	public void unload() {
		rootGroup.getChildren().remove(0);
		super.unload();
	}
	
	protected void addInputEvents() {
		super.addInputEvents();
		login.setOnAction(onPressHandlerLogin);
		back.setOnAction(onPressHandlerBack);
	}
	
	protected void removeInputEvents() {
		super.removeInputEvents();
		login.removeOnAction(onPressHandlerLogin);
		back.removeOnAction(onPressHandlerBack);
	}
}
