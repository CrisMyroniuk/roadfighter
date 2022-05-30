package roadfighter;

import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import roadfighter.interfaces.Collidator;
import roadfighter.interfaces.Collideable;
import roadfighter.objects.CarPlayer;
import roadfighter.objects.Obstacle;
import roadfighter.objects.Player;
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
	private CarPlayer car;
	private Obstacle obstacle;
	
	private EventHandler<KeyEvent> keyReleasedHandler;
	
	private GameObjectBuilder GOBuilder;
	
	public GameSceneHandler(RoadFighterGame g) {
		super(g);
		GOBuilder = GameObjectBuilder.getInstance();
	}
	
	public void load(boolean fullStart) {
		Group rootGroup = new Group();
		scene.setRoot(rootGroup);

		car = new CarPlayer(100.0, 500.0);
		player = new Player(car);
		obstacle = new Obstacle(100.0, 200.0);
		
		GOBuilder = GameObjectBuilder.getInstance();
		GOBuilder.setRootNode(rootGroup);
		GOBuilder.add(player, car, obstacle);
		
		if (fullStart) {
			addTimeEventsAnimationTimer();
			addInputEvents();
		}
	}

	@Override
	protected void prepareScene() {
		Group rootGroup = new Group();
		scene = new Scene(rootGroup, Config.baseWidth, Config.baseHeight, Color.BLACK);
	}

	@Override
	protected void defineEventHandlers() {
		//los event handlers acceden directamente al auto pero no se si esta bien eso
		mouseEventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
			}
		};
		keyEventHandler = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				switch (e.getCode()) {
				case W:
					System.out.println("esta acelerando");
					break;
				case A:
					car.setDirectionLeft();
					break;
				case S:
					break;
				case D:
					car.setDirectionRight();
					break;
				case E:
					break;
				case R:
					break;
				case ESCAPE:
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
					break;
				case A:
					car.setDirectionUp();
					break;
				case S:
					break;
				case D:
					car.setDirectionUp();
					break;
				case E:
					break;
				case R:
					break;
				case ESCAPE:
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
