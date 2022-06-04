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
import roadfighter.objects.BottomLimit;
import roadfighter.objects.CarPlayer;
import roadfighter.objects.Coordinate;
import roadfighter.objects.Direction;
import roadfighter.objects.GoodDriver;
import roadfighter.objects.LeftLimit;
import roadfighter.objects.Obstacle;
import roadfighter.objects.Player;
import roadfighter.objects.PlayerState;
import roadfighter.objects.PowerDown;
import roadfighter.objects.PowerUp;
import roadfighter.objects.RightLimit;
import roadfighter.objects.TopLimit;
import roadfighter.objects_menu.GenericText;
import roadfighter.objects_menu.ScoreText;
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
	private GenericText score;

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
	private Group rootGroup;
	private AudioClip audioGame;
	
	private boolean winnerExists = false;
	private String winner = "nadie (algo salio mal)";

	public GameSceneHandler(RoadFighterGame g,boolean singlePlayer) {
		super(g);
		GOBuilder = GameObjectBuilder.getInstance();
		random = new Random();
		this.singlePlayer = singlePlayer;
	}

	public void load() {
		
		String src = "file:src/resources/sounds/gameSound.mp3";
		
		audioGame = new AudioClip(src);
		
		audioGame.setVolume(Config.masterVolumeModifier * Config.musicVolumeModifier);
		audioGame.play();
		
		rootGroup = new Group();
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
		players.add(new Player(new CarPlayer(515.0, 750.0, "file:src/resources/images/Player.png"),keysPlayerOne));
		
		//si son dos jugadores
		if(!singlePlayer) {
			//teclas movimiento jugador 2
			keysPlayerTwo.add(KeyCode.UP);
			keysPlayerTwo.add(KeyCode.LEFT);
			keysPlayerTwo.add(KeyCode.DOWN);
			keysPlayerTwo.add(KeyCode.RIGHT);
			
			//tecla poder jugador 2
			keysPlayerTwo.add(KeyCode.L);
			
			players.add(new Player(new CarPlayer(1000, 750.0,"file:src/resources/images/Player2.png"),keysPlayerTwo));
		}
		
		//obstacles.add(new Obstacle(825.0, 200.0,"file:src/resources/images/ObstacleSprite.png"));

		enemy = new BadDriver(990.0, 0.0, Direction.UP);
		spawnTimer = 1;

		GOBuilder.setRootNode(rootGroup);
		gameObjects.add(background);
		
		for(Player p : players) {
			gameObjects.add(p.getCarPlayer());
		}
		
		score = new ScoreText(players, new Coordinate(50, 50));
		gameObjects.add(score);
		
		gameObjects.add(new TopLimit());
		gameObjects.add(new BottomLimit());
		gameObjects.add(new LeftLimit());
		gameObjects.add(new RightLimit());
		
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
				
				if(e.getCode() == KeyCode.ESCAPE) {
					audioGame.stop();
					g.startMenu();
				}
				
				if(e.getCode() == KeyCode.R) {
					audioGame.stop();
					g.startMenu();
					g.startGame(singlePlayer);
				}
				
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
				
				if(e.getCode() == KeyCode.ESCAPE) {
					audioGame.stop();
					g.startMenu();
				}
				
				if(e.getCode() == KeyCode.R) {
				audioGame.stop();
				g.startMenu();
				g.startGame(singlePlayer);
			}
				
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
		if(players.get(0).getCarPlayer().getPlayerState() == PlayerState.PLAYER_DEATH && singlePlayer) {
			audioGame.stop();
			g.startMenu(false);
			g.startGame(singlePlayer);
		}
		spawnTimer -= delta;
		if (spawnTimer <= 0) {
			//ahora se eliminan solos cuando su coordenada en Y llega a la altura de la pantalla + 500
			ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
			int selecGO = random.nextInt(1,5);
			switch(selecGO) {
				case 1:
					gameObjects.add(new Obstacle(random.nextDouble(515, 990), -50,"file:src/resources/images/Conito.png"));
					break;
				case 2:
					gameObjects.add(new BadDriver(random.nextDouble(515, 990), -50, Direction.UP));
					break;
				case 3:
					gameObjects.add(new PowerUp(random.nextDouble(515, 990), -50.0, 100,"file:src/resources/images/coin.png"));
					break;
				case 4:
					gameObjects.add(new PowerDown(random.nextDouble(515, 990), -50.0,"file:src/resources/images/velocityDown.png"));
					break;
			}
			
	
			GOBuilder.add(gameObjects);

			spawnTimer = random.nextDouble(0.5, 1.5);
		}
		
		checkCollisions();
		
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getPoint() >= Config.maxScore) {
				winnerExists = true;
				winner = "PLAYER " + (i + 1);//los player podrian tener un nombre eventualmente
				break;
			}
		}
		
		if (winnerExists) {
			g.startWin(winner, singlePlayer);
		}
		
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

	public void unload() {
		audioGame.stop();
		
		rootGroup.getChildren().remove(0);
		super.unload();
	}
}
