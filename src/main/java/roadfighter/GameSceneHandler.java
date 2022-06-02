package roadfighter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
import roadfighter.objects.Background;
import roadfighter.objects.BadDriver;
import roadfighter.objects.CarPlayer;
import roadfighter.objects.Direction;
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
	
	private Background background;
	
	private Player player;
	private CarPlayer car;
	private Obstacle obstacle;
	
	private BadDriver enemy;
	private Random random;
	private double spawnTimer;
	
	private EventHandler<KeyEvent> keyReleasedHandler;
	
	private GameObjectBuilder GOBuilder;
	
	public GameSceneHandler(RoadFighterGame g) {
		super(g);
		GOBuilder = GameObjectBuilder.getInstance();
		random = new Random();
	}
	
	public void load(boolean fullStart) {
		Group rootGroup = new Group();
		scene.setRoot(rootGroup);
		
		background = new Background();

		player = new Player();
		car = player.newCar(300.0, 600.0);
		obstacle = new Obstacle(100.0, 200.0);
		
		enemy = new BadDriver(200.0, 0.0, Direction.UP);
		spawnTimer = 1;
		
		GOBuilder.setRootNode(rootGroup);
		GOBuilder.add(player, car, obstacle, enemy, background);
		
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
					player.input(Direction.UP, true);
					break;
				case A:
					player.input(Direction.LEFT, true);
					break;
				case S:
					player.input(Direction.DOWN, true);
					break;
				case D:
					player.input(Direction.RIGHT, true);
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
					player.input(Direction.UP, false);
					break;
				case A:
					player.input(Direction.LEFT, false);
					break;
				case S:
					player.input(Direction.DOWN, false);
					break;
				case D:
					player.input(Direction.RIGHT, false);
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
		
		// TODO esto no elimina nada, era para probar si funciona, habria que meterlo en una clase aparte que spawnee enemigos
		// para eliminarlos podemos meter algo como el ColliderTop para que cuando salgan de la pantalla se eliminen,
		// o que en el update() de los obstaculos/enemigos pregunte por la coordenada en Y y se destruya solo cuando este fuera de la pantalla
		spawnTimer -= delta;
		if (spawnTimer <= 0) {
			GOBuilder.add(new Obstacle(random.nextDouble(0, 500), -50), new BadDriver(random.nextDouble(0, 500), -50, Direction.UP));
			
			spawnTimer = random.nextDouble(1, 3);
		}
		
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
