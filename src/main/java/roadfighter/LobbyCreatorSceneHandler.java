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
import roadfighter.objects_menu.MenuComboBox;
import roadfighter.objects_menu.MenuTextField;
import roadfighter.objects_menu.Title;
import roadfighter.utils.GameObject;
import roadfighter.utils.GameObjectBuilder;

public class LobbyCreatorSceneHandler extends SceneHandler {
	private Group rootGroup;
	private Background background;
	private ArrayList<GameObject> gameObjects=new ArrayList<GameObject>();
	private Title title;
	private GameObjectBuilder gameOB;
	
	private MenuTextField nameTextField;
	private MenuComboBox maxPlayersComboBox;
	private ButtonMenu create;
	private ButtonMenu back;
		
	private EventHandler<ActionEvent> onPressHandlerCreate;
	private EventHandler<ActionEvent> onPressHandlerBack;
	
	public LobbyCreatorSceneHandler(RoadFighterGame g) {
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
					g.startOnlineMenu(g.getInput(), g.getOutput());
					break;
				default:
					break;
				}
			}
		};
		
		onPressHandlerCreate = new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) {
				attemptCreateLobby();
			}
		};
		
		onPressHandlerBack = new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) {
				g.startOnlineMenu(g.getInput(), g.getOutput());
			}
		};
	}
	
	private void attemptCreateLobby() {
		DataOutputStream output = g.getOutput();
		DataInputStream input = g.getInput();
		
		try {
			output.writeUTF(new Message(MessageType.LOBBY_NEW, maxPlayersComboBox.getValue().toString()).getJsonString());
			output.writeUTF(new Message(MessageType.LOBBY_NEW, nameTextField.getText()).getJsonString());
			Message response = new Gson().fromJson(input.readUTF(), Message.class);
			if (response.getType().equals(MessageType.LOBBY_NEW)) {
				if (response.getContent().equals("created"))
					g.startLobbyScreen();
				else if (response.getContent().equals("inUse"))
					throw new Exception();
			}
		} catch (IOException e) {
			System.err.println("ocurrio algun error");
		} catch (Exception e) {
			nameTextField.setText("nombre en uso");
			System.err.println("ya existe un lobby con el mismo nombre");
		}
	}

	public void load() {
		Group baseGroup = new Group();
		rootGroup.getChildren().add(baseGroup);
		
		gameOB = GameObjectBuilder.getInstance();
		gameOB.setRootNode(baseGroup);
		
		background = new Background();

		title = new Title();
				
		nameTextField = new MenuTextField("Lobby name", 500, Config.baseHeight * 3 / 5);
		
		maxPlayersComboBox = new MenuComboBox("Max players", 500, Config.baseHeight * 3 / 5 + 100);
		maxPlayersComboBox.addItem(2);
		maxPlayersComboBox.addItem(3);
		maxPlayersComboBox.addItem(4);
		maxPlayersComboBox.setDefaultValue();
		
		create = new ButtonMenu("CREATE", Config.baseHeight * 3 / 5 + 200);
	
		back = new ButtonMenu("BACK", Config.baseHeight * 3 / 5 + 300);
		
		gameOB.add(nameTextField, maxPlayersComboBox, create, back);
		
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
		create.setOnAction(onPressHandlerCreate);
		back.setOnAction(onPressHandlerBack);
	}
	
	protected void removeInputEvents() {
		super.removeInputEvents();
		create.removeOnAction(onPressHandlerCreate);
		back.removeOnAction(onPressHandlerBack);
	}
}
