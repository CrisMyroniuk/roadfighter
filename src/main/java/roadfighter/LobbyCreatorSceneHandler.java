package roadfighter;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
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
					g.startMenu();
					break;
				default:
					break;
				}
			}
		};
		
		onPressHandlerCreate = new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) {
				String name = nameTextField.getText();
				Integer max = maxPlayersComboBox.getValue();

				// aca voy a tener que hablar con el servidor para que cree el lobby
				// va a recibir los datos para dibujarlo y se los va a pasar al startLobbyScreen
				
				g.startLobbyScreen();
			}
		};
		
		onPressHandlerBack = new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) {
				g.startMenu();
			}
		};
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
