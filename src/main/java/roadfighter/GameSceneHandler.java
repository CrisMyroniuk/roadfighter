package roadfighter;

import java.util.ArrayList;
import java.util.List;

//import javax.swing.text.JTextComponent.KeyBinding;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
//import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
//import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import roadfighter.interfaces.Collidator;
import roadfighter.interfaces.Collideable;
import roadfighter.objects.CarPlayer;
import roadfighter.objects.Obstacle;
import roadfighter.objects.Player;
import roadfighter.utils.GameObject;
import roadfighter.utils.GameObjectBuilder;

public class GameSceneHandler extends SceneHandler {
	//private KeyCode playerOneUpKey = KeyCode.W;
	//private KeyCode playerOneLeftKey = KeyCode.A;
	//private KeyCode playerOneDownKey = KeyCode.S;
	//private KeyCode playerOneRightKey = KeyCode.D;
	/* se podria hacer algo asi para poder configurar las teclas
	 * pero no se donde poner estas variables y si lo hacemos asi
	 * hay que usar if-else porque no deja poner las variables esas
	 * en los casos del switch por algun motivo que no entendi muy bien
	*/
	
	private Player player;
	private ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	
	private EventHandler<KeyEvent> keyReleasedHandler;
	
	private GameObjectBuilder GOBuilder;
	
	public GameSceneHandler(RoadFighterGame g) {
		super(g);
		GOBuilder = GameObjectBuilder.getInstance();
	}
	
	public void load() {
		Group rootGroup = new Group();
		scene.setRoot(rootGroup);
		player=new Player(new CarPlayer(200.0,500.0));
		//players.add(new Player(new CarPlayer(200.0,500.0)));
		//car = new CarPlayer(100.0, 500.0);
		//player = new Player(car);
		obstacles.add(new Obstacle(100.0, 500.0));
		obstacles.add(new Obstacle(200.0, 200.0));
		
//		for (Player player : players) {
//			gameObjects.add(player);
//		}
		gameObjects.add(player.getCarPlayer());
		for (Obstacle obstacle : obstacles) {
			gameObjects.add(obstacle);
		}
		GOBuilder = GameObjectBuilder.getInstance();
		GOBuilder.setRootNode(rootGroup);
		GOBuilder.add(gameObjects);
		
//		if (fullStart) {
		addTimeEventsAnimationTimer();
		addInputEvents();
//		}
	}

	@Override
	protected void prepareScene() {
		Group rootGroup = new Group();
		scene = new Scene(rootGroup, Config.baseWidth, Config.baseHeight, Color.BLACK);
	}

	@Override
	protected void defineEventHandlers() {
		keyEventHandler = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				switch (e.getCode()) {
				case W:
					player.getCarPlayer().setDirectionUp();
					break;
				case A:
					player.getCarPlayer().setDirectionLeft();
					break;
				case S:
					player.getCarPlayer().setDirectionDown();
					break;
				case D:
					player.getCarPlayer().setDirectionRight();
					break;
				case E:
					break;
				default:
					break;
				}
			}
		};
		
		keyReleasedHandler = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				switch (e.getCode()) {
				case W:
					System.out.println("dejo de acelerar");
					player.getCarPlayer().setDirectionNone();
					break;
				case A:
					player.getCarPlayer().setDirectionNone();
					break;
				case S:
					player.getCarPlayer().setDirectionNone();
					break;
				case D:
					player.getCarPlayer().setDirectionNone();
					break;
				case E:
					break;
				default:
					break;
				}
			}
		};
		
	}
	
	@Override
	protected void addInputEvents() {
		super.addInputEvents();
		scene.addEventHandler(KeyEvent.KEY_RELEASED, keyReleasedHandler);
	}
	
	@Override
	public void update(double delta) {
		super.update(delta);
		checkCollisions();
		//aca va cualquier cosa que no se haga en el metodo update()
		//de los updateables
	}
	
	private void checkCollisions() {
		// copie el codigo de flappy bird porque no entendi muy bien la logica de esto
		List<Collidator> collidators = GOBuilder.getCollidators();
		List<Collideable> collideables = GOBuilder.getCollideables();
		
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
