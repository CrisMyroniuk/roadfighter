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
import roadfighter.objects_menu.ButtonMenu;
import roadfighter.objects_menu.Title;
import roadfighter.utils.GameObject;
import roadfighter.utils.GameObjectBuilder;

public class WinSceneHandler extends SceneHandler {

	private Group rootGroup;
	private AudioClip audioGame;
	private Background background;
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	private Title title;
	private GameObjectBuilder gameOB;
	
	private ButtonMenu restartButton;
	private ButtonMenu menuButton;
	
	private String winner;
	private boolean singlePlayer;
	
	private EventHandler<ActionEvent> onPressHandlerRestart;
	private EventHandler<ActionEvent> onPressHandlerMenu;
	
	public WinSceneHandler(RoadFighterGame g, String winner, boolean singlePlayer) {
		super(g);
		this.singlePlayer = singlePlayer;
		this.winner = winner;
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
		
		onPressHandlerRestart = new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) {
				g.startGame(singlePlayer);
			}
		};
		
		onPressHandlerMenu = new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) {
				g.startMenu();
			}
		};
	}

	public void load() {
		boolean fullStart = true;
		//esto esta re al pedo porque siempre queres el fullstart
		//(siempre va al menu desde la escena del juego, si cambia eso hay que pasarle un boolean)
		Group baseGroup = new Group();
		rootGroup.getChildren().add(baseGroup);
		String src = "file:src/resources/sounds/menuSound.mp3";
		
		gameOB = GameObjectBuilder.getInstance();
		gameOB.setRootNode(baseGroup);

//		audioGame = new AudioClip(src);
//		audioGame.play();
		
		background = new Background();

		title = new Title(winner + "WINS!");
		
		restartButton = new ButtonMenu("RESTART", Config.baseHeight * 3 / 5);
		menuButton = new ButtonMenu("MENU", Config.baseHeight * 3 / 5 + 50);
		
		gameOB.add(restartButton, menuButton);
		
		gameObjects.add(background);
		gameObjects.add(title);
		gameOB.add(gameObjects);

		if (fullStart) {
			addTimeEventsAnimationTimer();
			addInputEvents();
		}
	}
	
	@Override
	public void update(double delta) {
		super.update(delta);
		checkCollisions();
		//aca va cualquier cosa que no se haga en el metodo update()
		//de los updateables
	}

	public void unload() {
		
		audioGame.stop();
		
		rootGroup.getChildren().remove(0);
		super.unload();
	}
	
	protected void addInputEvents() {
		super.addInputEvents();
		restartButton.setOnAction(onPressHandlerRestart);
		menuButton.setOnAction(onPressHandlerMenu);
	}
	
	protected void removeInputEvents() {
		super.removeInputEvents();
		restartButton.removeOnAction(onPressHandlerRestart);
		menuButton.removeOnAction(onPressHandlerMenu);
	}
	
	private void checkCollisions() {
		// copie el codigo de flappy bird porque no entendi muy bien la logica de esto
		List<Collidator> collidators = gameOB.getCollidators();
		List<Collideable> collideables = gameOB.getCollideables();
		
		for (int i = 0; i < collidators.size(); i++) {
			Collidator collidator = collidators.get(i);
			for (int j = i + 1; j < collidators.size(); j++) {
				Collidator otherCollidator = collidators.get(j);
				Shape intersect = Shape.intersect(collidator.getCollider(), otherCollidator.getCollider());
				if (intersect.getBoundsInLocal().getWidth() != -1) {
					collidator.collide(otherCollidator);
					otherCollidator.collide(collidator);
				}
			}

			for (int j = 0; j < collideables.size(); j++) {
				Collideable collideable = collideables.get(j);
				Shape intersect = Shape.intersect(collidator.getCollider(), collideable.getCollider());

				// TODO test times
				// XXX Si el substract es distinto???
				// Check intersects
				if (intersect.getBoundsInLocal().getWidth() != -1) {
					collidator.collide(collideable);
				} else {
					// Check contains
					Bounds collideableBounds = collideable.getCollider().getBoundsInLocal();
					Bounds collidatorBounds = collidator.getCollider().getBoundsInLocal();
					if (collideableBounds.contains(collidatorBounds.getCenterX(), collidatorBounds.getCenterY())) {
						collidator.collide(collideable);
					}
				}
			}
		}
	}
	
}
