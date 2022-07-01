package roadfighter;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import roadfighter.interfaces.Collidator;
import roadfighter.interfaces.Collideable;
import roadfighter.objects.Background;
import roadfighter.objects.ColliderTop;
import roadfighter.objects.Direction;
import roadfighter.objects.Enemy;
import roadfighter.objects.GoodDriver;
import roadfighter.objects_menu.ButtonMenu;
import roadfighter.objects_menu.MenuComboBox;
import roadfighter.objects_menu.MenuTable;
import roadfighter.objects_menu.MenuTextField;
import roadfighter.objects_menu.Title;
import roadfighter.utils.GameObject;
import roadfighter.utils.GameObjectBuilder;

public class LobbySelectorSceneHandler extends SceneHandler {
	private Group rootGroup;
	private Background background;
	private ArrayList<GameObject> gameObjects=new ArrayList<GameObject>();
	private Title title;
	private GameObjectBuilder gameOB;
	
	private ButtonMenu back;
	
	private EventHandler<ActionEvent> onPressHandlerBack;
	private MenuTable lobbyTable;
	
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
		
		lobbyTable = new MenuTable(700, Config.baseHeight * 3 / 5 - 200);
				
		back = new ButtonMenu("BACK", Config.baseHeight * 3 / 5 + 300);
		
		gameOB.add(lobbyTable, back);
		
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
		back.setOnAction(onPressHandlerBack);
	}
	
	protected void removeInputEvents() {
		super.removeInputEvents();
		back.removeOnAction(onPressHandlerBack);
	}
}
