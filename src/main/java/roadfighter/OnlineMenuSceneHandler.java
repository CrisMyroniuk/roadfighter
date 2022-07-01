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
import roadfighter.objects_menu.Title;
import roadfighter.utils.GameObject;
import roadfighter.utils.GameObjectBuilder;

public class OnlineMenuSceneHandler extends SceneHandler{
	private Group rootGroup;
	private Background background;
	private ArrayList<GameObject> gameObjects=new ArrayList<GameObject>();
	private Title title;
	private GameObjectBuilder gameOB;
	
	private ButtonMenu createLobby;
	private ButtonMenu joinLobby;
	private ButtonMenu back;
		
	private EventHandler<ActionEvent> onPressHandlerCreate;
	private EventHandler<ActionEvent> onPressHandlerJoin;
	private EventHandler<ActionEvent> onPressHandlerBack;
	
	public OnlineMenuSceneHandler(RoadFighterGame g) {
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
				g.startLobbyCreatorMenu();
			}
		};
		
		onPressHandlerJoin = new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) {
				g.startLobbySelectorMenu();
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
		
		createLobby = new ButtonMenu("CREATE NEW LOBBY", Config.baseHeight * 3 / 5 + 100);
		joinLobby = new ButtonMenu("JOIN EXISTING LOBBY", Config.baseHeight * 3 / 5 + 200);
		back = new ButtonMenu("BACK", Config.baseHeight * 3 / 5 + 300);
		
		gameOB.add(createLobby, joinLobby, back);
		
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
		createLobby.setOnAction(onPressHandlerCreate);
		joinLobby.setOnAction(onPressHandlerJoin);
		back.setOnAction(onPressHandlerBack);
	}
	
	protected void removeInputEvents() {
		super.removeInputEvents();
		createLobby.removeOnAction(onPressHandlerCreate);
		joinLobby.removeOnAction(onPressHandlerJoin);
		back.removeOnAction(onPressHandlerBack);
	}
}
