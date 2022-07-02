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
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import roadfighter.client.utils.Message;
import roadfighter.interfaces.Collidator;
import roadfighter.interfaces.Collideable;
import roadfighter.objects.Background;
import roadfighter.objects.BadDriver;
import roadfighter.objects.BottomLimit;
import roadfighter.objects.CarPlayer;
import roadfighter.objects.Coordinate;
import roadfighter.objects.Direction;
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
import roadfighter.threads.ListenerThread;
import roadfighter.utils.GameObject;
import roadfighter.utils.GameObjectBuilder;

public class OnlineGameSceneHandler extends SceneHandler {

	private Background background;
	private GenericText score;

	private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();

	private ArrayList<KeyCode> keysPlayerOne = new ArrayList<KeyCode>();
	private ArrayList<KeyCode> keysPlayerTwo = new ArrayList<KeyCode>();
	private BadDriver enemy;

	private EventHandler<KeyEvent> keyReleasedHandler;

	private GameObjectBuilder GOBuilder;
	private Group rootGroup;
	private AudioClip audioGame;
	
	private ListenerThread servidor;

	public OnlineGameSceneHandler(RoadFighterGame g, ListenerThread servidor) {
		super(g);
		GOBuilder = GameObjectBuilder.getInstance();
		this.servidor = servidor;
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
		players.add(new Player(new CarPlayer(515.0, 750.0, "file:src/resources/images/CarPink.png"),keysPlayerOne));
		
		//teclas movimiento jugador 2
		keysPlayerTwo.add(KeyCode.UP);
		keysPlayerTwo.add(KeyCode.LEFT);
		keysPlayerTwo.add(KeyCode.DOWN);
		keysPlayerTwo.add(KeyCode.RIGHT);
			
		//tecla poder jugador 2
		keysPlayerTwo.add(KeyCode.L);
			
		players.add(new Player(new CarPlayer(1000, 750.0,"file:src/resources/images/Player2.png"),keysPlayerTwo));
		
		
		//obstacles.add(new Obstacle(825.0, 200.0,"file:src/resources/images/ObstacleSprite.png"));

		enemy = new BadDriver(990.0, 0.0, Direction.UP);

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
		
		

		addTimeEventsAnimationTimer();
		addInputEvents();
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
		Message message = servidor.pollGameMessage();
		switch(message.getType()) {
		case ITEM_NEW:
			GOBuilder.add(new PowerUp(Double.parseDouble(message.getContent()), -50.0, 100, "file:src/resources/images/coin.png"));
			break;
		default:
			break;
		}
	}

	public void unload() {
		audioGame.stop();
		
		rootGroup.getChildren().remove(0);
		super.unload();
	}
}