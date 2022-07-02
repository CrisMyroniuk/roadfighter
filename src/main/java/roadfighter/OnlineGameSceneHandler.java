package roadfighter;

import javafx.scene.media.AudioClip;

import java.io.IOException;
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
import roadfighter.objects.PlayerOnline;
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
	private PlayerOnline player1;
	private PlayerOnline player2;

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
		
		player1 = new PlayerOnline(new CarPlayer(Double.parseDouble(servidor.pollGameMessage().getContent()), 750.0, "file:src/resources/images/CarPink.png"), g.getOutput());
		player2 = new PlayerOnline(new CarPlayer(Double.parseDouble(servidor.pollGameMessage().getContent()), 750.0,"file:src/resources/images/Player2.png"), keysPlayerOne);
		
		//obstacles.add(new Obstacle(825.0, 200.0,"file:src/resources/images/ObstacleSprite.png"));

		GOBuilder.setRootNode(rootGroup);
		gameObjects.add(background);
		
//		score = new ScoreText(new Coordinate(50, 50));
//		gameObjects.add(score);
		
		gameObjects.add(new TopLimit());
		gameObjects.add(new BottomLimit());
		gameObjects.add(new LeftLimit());
		gameObjects.add(new RightLimit());
		GOBuilder.add(player1.getCarPlayer(), player2.getCarPlayer());
		
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
				
				try {
					player1.eventPressed(e);
				} catch (IOException e1) {
					e1.printStackTrace();
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
				
				try {
					player1.eventReleased(e);
				} catch (IOException e1) {
					e1.printStackTrace();
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
		Message message;
		while ((message = servidor.pollGameMessage()) != null) {
			if (message != null) {
				switch(message.getType()) {
				case ITEM_NEW:
					GOBuilder.add(new PowerUp(Double.parseDouble(message.getContent().replace(',', '.')), -50.0, 100, "file:src/resources/images/coin.png"));
					break;
				case ENEMY_NEW:
					GOBuilder.add(new BadDriver(Double.parseDouble(message.getContent().replace(',', '.')), -50.0, Direction.UP));
					break;
				case OBSTACLE_NEW:
					GOBuilder.add(new Obstacle(Double.parseDouble(message.getContent().replace(',', '.')), -50.0, "file:src/resources/images/Conito.png"));
					break;
				case PDOWN_NEW:
					GOBuilder.add(new PowerDown(Double.parseDouble(message.getContent().replace(',', '.')), -50.0, "file:src/resources/images/velocityDown.png"));
					break;
				case PLAYER:
					String[] p1Coord = message.getContent().replace(',', '.').split("|");
					player1.getCarPlayer().setCoordinate(Double.parseDouble(p1Coord[0]), Double.parseDouble(p1Coord[1]));
					break;
				case PLAYER_OTHER:
					String[] p2Coord = message.getContent().replace(',', '.').split("|");
					player2.getCarPlayer().setCoordinate(Double.parseDouble(p2Coord[0]), Double.parseDouble(p2Coord[1]));					
					break;
//				case PLAYER_MOVE:
//					if (message.getContent().equals("up"))
//						player1.eventPressed(KeyCode.W);
//					else if (message.getContent().equals("left"))
//						player1.eventPressed(KeyCode.A);
//					else if (message.getContent().equals("down"))
//						player1.eventPressed(KeyCode.S);
//					else if (message.getContent().equals("right"))
//						player1.eventPressed(KeyCode.D);
//					break;
//				case PLAYER_STOP:
//					if (message.getContent().equals("up"))
//						player1.eventReleased(KeyCode.W);
//					else if (message.getContent().equals("left"))
//						player1.eventReleased(KeyCode.A);
//					else if (message.getContent().equals("down"))
//						player1.eventReleased(KeyCode.S);
//					else if (message.getContent().equals("right"))
//						player1.eventReleased(KeyCode.D);
//					break;
//				case PLAYER_OTHER_MOVE:
//					if (message.getContent().equals("up"))
//						player2.eventPressed(KeyCode.W);
//					else if (message.getContent().equals("left"))
//						player2.eventPressed(KeyCode.A);
//					else if (message.getContent().equals("down"))
//						player2.eventPressed(KeyCode.S);
//					else if (message.getContent().equals("right"))
//						player2.eventPressed(KeyCode.D);
//					break;
//				case PLAYER_OTHER_STOP:
//					if (message.getContent().equals("up"))
//						player2.eventReleased(KeyCode.W);
//					else if (message.getContent().equals("left"))
//						player2.eventReleased(KeyCode.A);
//					else if (message.getContent().equals("down"))
//						player2.eventReleased(KeyCode.S);
//					else if (message.getContent().equals("right"))
//						player2.eventReleased(KeyCode.D);
//					break;
				default:
					break;
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