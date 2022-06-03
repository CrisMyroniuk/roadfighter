package roadfighter;

import javafx.scene.media.AudioClip;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//import javax.swing.text.JTextComponent.KeyBinding;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
//import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
//import javafx.scene.input.MouseEvent;
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
	// private KeyCode playerOneUpKey = KeyCode.W;
	// private KeyCode playerOneLeftKey = KeyCode.A;
	// private KeyCode playerOneDownKey = KeyCode.S;
	// private KeyCode playerOneRightKey = KeyCode.D;
	/*
	 * se podria hacer algo asi para poder configurar las teclas pero no se donde
	 * poner estas variables y si lo hacemos asi hay que usar if-else porque no deja
	 * poner las variables esas en los casos del switch por algun motivo que no
	 * entendi muy bien
	 */

	private Background background;

	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

	private ArrayList<KeyCode> keysPlayerOne = new ArrayList<KeyCode>();
	private ArrayList<KeyCode> keysPlayerTwo = new ArrayList<KeyCode>();
	private BadDriver enemy;
	private Random random;
	private double spawnTimer;
	
	private boolean singlePlayer;

	private EventHandler<KeyEvent> keyReleasedHandler;

	private GameObjectBuilder GOBuilder;

	public GameSceneHandler(RoadFighterGame g,boolean singlePlayer) {
		super(g);
		GOBuilder = GameObjectBuilder.getInstance();
		random = new Random();
		this.singlePlayer = singlePlayer;
	}

	public void load() {
		
		String src = "file:src/resources/sounds/gameSound.mp3";
		
		AudioClip audioGame = new AudioClip(src);
		
		audioGame.play();
		
		Group rootGroup = new Group();
		scene.setRoot(rootGroup);

		background = new Background();
		// R1 515
		// R2 675
		// R3 825
		// R4 990
		
		//teclas movimiento jugador 1
		keysPlayerOne.add(KeyCode.W);
		keysPlayerOne.add(KeyCode.A);
		keysPlayerOne.add(KeyCode.S);
		keysPlayerOne.add(KeyCode.D);
		
		//tecla poder jugador 1
		keysPlayerOne.add(KeyCode.C);
		players.add(new Player(new CarPlayer(515.0, 750.0),keysPlayerOne));
		
		//si son dos jugadores
		if(!singlePlayer) {
			//teclas movimiento jugador 2
			keysPlayerTwo.add(KeyCode.UP);
			keysPlayerTwo.add(KeyCode.LEFT);
			keysPlayerTwo.add(KeyCode.DOWN);
			keysPlayerTwo.add(KeyCode.RIGHT);
			
			//tecla poder jugador 2
			keysPlayerTwo.add(KeyCode.L);
			
			players.add(new Player(new CarPlayer(1000, 750.0),keysPlayerTwo));
		}
		
		obstacles.add(new Obstacle(825.0, 200.0,"file:src/resources/images/ObstacleSprite.png"));

		enemy = new BadDriver(990.0, 0.0, Direction.UP);
		spawnTimer = 1;

		GOBuilder.setRootNode(rootGroup);
		gameObjects.add(background);
		
		for(Player p : players) {
			gameObjects.add(p.getCarPlayer());
		}
		
		gameObjects.add(enemy);
		for (Obstacle obstacle : obstacles) {
			gameObjects.add(obstacle);
		}
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
				
				for(Player p : players) {
					p.eventPressed(e);
				}
				/*
				 * switch (e.getCode()) { case W: player.getCarPlayer().setDirectionUp(); break;
				 * case A: player.getCarPlayer().setDirectionLeft(); break; case S:
				 * player.getCarPlayer().setDirectionDown(); break; case D:
				 * player.getCarPlayer().setDirectionRight(); break; case E: break; default:
				 * break; }
				 */
			}
		};

		keyReleasedHandler = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				
				for(Player p : players) {
					p.eventReleased(e);
				}
				/*
				 * switch (e.getCode()) { case W: System.out.println("dejo de acelerar");
				 * player.getCarPlayer().setDirectionNone(Direction.UP); break; case A:
				 * player.getCarPlayer().setDirectionNone(Direction.LEFT); break; case S:
				 * player.getCarPlayer().setDirectionNone(Direction.RIGHT); break; case D:
				 * player.getCarPlayer().setDirectionNone(Direction.DOWN); //
				 * player.input(Direction.UP, false); // break; // case A: //
				 * player.input(Direction.LEFT, false); // break; // case S: //
				 * player.input(Direction.DOWN, false); // break; // case D: //
				 * player.input(Direction.RIGHT, false); break; default: break; }
				 */
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

		// TODO esto no elimina nada, era para probar si funciona, habria que meterlo en
		// una clase aparte que spawnee enemigos
		// para eliminarlos podemos meter algo como el ColliderTop para que cuando
		// salgan de la pantalla se eliminen,
		// o que en el update() de los obstaculos/enemigos pregunte por la coordenada en
		// Y y se destruya solo cuando este fuera de la pantalla
		spawnTimer -= delta;
		if (spawnTimer <= 0) {
			ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
			gameObjects.add(new Obstacle(random.nextDouble(515, 990), -50,"file:src/resources/images/ObstacleSprite.png"));
			gameObjects.add(new BadDriver(random.nextDouble(515, 990), -50, Direction.UP));
			GOBuilder.add(gameObjects);

			spawnTimer = random.nextDouble(1, 3);
		}
		
		checkCollisions();
		
		// aca va cualquier cosa que no se haga en el metodo update()
		// de los updateables
	}

	private void checkCollisions() {
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
