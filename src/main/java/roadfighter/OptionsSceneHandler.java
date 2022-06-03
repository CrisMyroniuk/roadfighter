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
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import roadfighter.interfaces.Collidator;
import roadfighter.interfaces.Collideable;
import roadfighter.objects.Background;
import roadfighter.objects.CarPlayer;
import roadfighter.objects.ColliderTop;
import roadfighter.objects.Direction;
import roadfighter.objects.Enemy;
import roadfighter.objects.GoodDriver;
import roadfighter.objects.Player;
import roadfighter.objects_menu.ButtonMenu;
import roadfighter.objects_menu.Title;
import roadfighter.utils.GameObject;
import roadfighter.utils.GameObjectBuilder;

public class OptionsSceneHandler extends SceneHandler{
	private Group rootGroup;
	private AudioClip audioGame;
	private Background background;
	private ArrayList<GameObject> gameObjects=new ArrayList<GameObject>();
	//private Ground ground;
	private Title title;
	private ButtonMenu boton1Player;
	private ButtonMenu boton2Player;
	//private CarPlayerFX player;
	private Player player;
	private CarPlayer car;
	private Rectangle colliderBottom;
	private EventHandler<ActionEvent> onPressHandlerOnePlayer;
	private EventHandler<ActionEvent> onPressHandlerTwoPlayer;
	private ColliderTop colliderTop;
	private GameObjectBuilder gameOB;
	private Enemy enemy1;
	private Enemy enemy2;
	private Enemy enemy3;
	private Enemy enemy4;
	
	public OptionsSceneHandler(RoadFighterGame g) {
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
				case ENTER:
					g.startGame(true);
					break;
				case Q:
				case ESCAPE:
					System.exit(0);
					break;
				default:
					break;
				}
			}
		};
		
		onPressHandlerOnePlayer = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				g.startGame(true);
			}
		};
		
		onPressHandlerTwoPlayer = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				g.startGame(false);
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
		
		audioGame = new AudioClip(src);
		audioGame.play();
		background = new Background();

		title = new Title();
		
		
//		boton1Player = new ButtonMenu("1 PLAYER",Config.baseHeight * 3 / 5);
//		boton2Player = new ButtonMenu("2 PLAYERS",(Config.baseHeight * 3 / 5) + 100);

		gameOB = GameObjectBuilder.getInstance();
		gameOB.setRootNode(baseGroup);
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
		boton1Player.setOnAction(onPressHandlerOnePlayer);
		boton2Player.setOnAction(onPressHandlerTwoPlayer);
	}
	
	protected void removeInputEvents() {
		super.removeInputEvents();
		boton1Player.removeOnAction(onPressHandlerOnePlayer);
		boton2Player.setOnAction(onPressHandlerTwoPlayer);
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
