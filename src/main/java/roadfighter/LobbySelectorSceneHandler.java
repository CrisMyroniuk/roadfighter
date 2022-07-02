package roadfighter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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

public class LobbySelectorSceneHandler extends SceneHandler {
	private Group rootGroup;
	private Background background;
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	private Title title;
	private GameObjectBuilder gameOB;

	private MenuTextField name;
	private ButtonMenu back;
	
	private EventHandler<ActionEvent> onPressHandlerJoin;
	private EventHandler<ActionEvent> onPressHandlerBack;
//	private MenuTable lobbyTable;
	private ButtonMenu join;
	
	public LobbySelectorSceneHandler(RoadFighterGame g) {
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
		
		onPressHandlerJoin = new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) {
				attemptJoinLobby();
			}
		};
		
		onPressHandlerBack = new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) {
				g.startMenu();
			}
		};
	}
	
	private void attemptJoinLobby() {
		DataOutputStream output = g.getOutput();
		DataInputStream input = g.getInput();
		
		try {
			output.writeUTF(new Message(MessageType.LOBBY_JOIN, name.getText()).getJsonString());
			Message response = new Gson().fromJson(input.readUTF(), Message.class);
			if (response.getType().equals(MessageType.LOBBY_JOIN)) {
				if (response.getContent().equals("joined"))
					g.startLobbyScreen();
				else if (response.getContent().equals("notFound"))
					throw new Exception();
			}
		} catch (IOException e) {
			System.err.println("ocurrio algun error");
		} catch (Exception e) {
			name.setText("no se encontro");
			System.err.println("no existe ese servidor");
		}
	}

	public void load() {
		Group baseGroup = new Group();
		rootGroup.getChildren().add(baseGroup);
		
		gameOB = GameObjectBuilder.getInstance();
		gameOB.setRootNode(baseGroup);
		
		background = new Background();

		title = new Title();
		
//		lobbyTable = new MenuTable(700, Config.baseHeight * 3 / 5 - 200);
		name = new MenuTextField("name", 500, Config.baseHeight * 3 / 5 + 100);
		
		join = new ButtonMenu("JOIN", Config.baseHeight * 3 / 5 + 200);
		back = new ButtonMenu("BACK", Config.baseHeight * 3 / 5 + 300);
		
//		gameOB.add(lobbyTable, back);
		gameOB.add(name, join, back);
		
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
		join.setOnAction(onPressHandlerJoin);
		back.setOnAction(onPressHandlerBack);
	}
	
	protected void removeInputEvents() {
		super.removeInputEvents();
		join.removeOnAction(onPressHandlerJoin);
		back.removeOnAction(onPressHandlerBack);
	}
}
