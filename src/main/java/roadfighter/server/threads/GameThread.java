package roadfighter.server.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.random.RandomGenerator;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import roadfighter.Config;
import roadfighter.RoadFighterGame;
import roadfighter.interfaces.Collidator;
import roadfighter.interfaces.Collideable;
import roadfighter.interfaces.Updatable;
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
import roadfighter.server.Lobby;
import roadfighter.server.utils.Message;
import roadfighter.server.utils.MessageType;
import roadfighter.utils.GameObject;
import roadfighter.utils.GameObjectBuilder;

public class GameThread extends Thread {
	protected final long NANOS_IN_SECOND = 1_000_000_000;
	protected final double NANOS_IN_SECOND_D = 1_000_000_000.0;
	
	protected int frames = 0;
	protected int last_fps_frame = 0;
	protected AtomicInteger fps = new AtomicInteger(0);
	
	protected AnimationTimer gameTimer;
	protected long previousNanoFrame;
	protected long previousNanoSecond;

	private ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
	private ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
	
	private Random random;
	private double spawnTimer;
	private double posTimer;

	private GameObjectBuilder GOBuilder;
	private Group rootGroup;
	
	private boolean winnerExists = false;
	private String winner = "nadie (algo salio mal)";
	
	private Lobby lobby;

	private Player player1;
	private Player player2;
	private Client[] clientes;
	
	public GameThread(Lobby lobby) {
		this.lobby = lobby;
		random = new Random();
		
		GOBuilder = GameObjectBuilder.getInstance();
	
		spawnTimer = 1;
		posTimer = 0.3;

		rootGroup = new Group();
		GOBuilder.setRootNode(rootGroup);
		
		gameObjects.add(new TopLimit());
		gameObjects.add(new BottomLimit());
		gameObjects.add(new LeftLimit());
		gameObjects.add(new RightLimit());
		
		clientes = new Client[2];
		clientes = lobby.getClientsList().toArray(clientes);
		
		player1 = new Player(new CarPlayer(515.0, 750.0));
		player1.setClient(clientes[0]);
		player2 = new Player(new CarPlayer(1000, 750.0));
		player2.setClient(clientes[1]);
		
		sendAllClients(new Message(MessageType.LOBBY_CONTROL, "start"));
		player1.getClient().notify(new Message(MessageType.PLAYER_OTHER, "515.0"));
		player1.getClient().notify(new Message(MessageType.PLAYER_OTHER, "1000.0"));
		player2.getClient().notify(new Message(MessageType.PLAYER_OTHER, "1000.0"));
		player2.getClient().notify(new Message(MessageType.PLAYER_OTHER, "515.0"));
		
		gameObjects.add(player1.getCarPlayer());
		gameObjects.add(player2.getCarPlayer());
		GOBuilder.add(gameObjects);

//		addTimeEventsAnimationTimer();
	}

	public void update(double delta) {
		frames++;

		List<Updatable> updatables = GameObjectBuilder.getInstance().getUpdatables();
		for (Updatable updatable : updatables) {
			updatable.update(delta);
		}
		
		Message action = player1.getClient().pollActions();
		if (action != null) {
			
			switch(action.getType()) {
			case PLAYER_MOVE:
				if (action.getContent().equals("up"))
					player1.eventPressed(KeyCode.W);
				else if (action.getContent().equals("left"))
					player1.eventPressed(KeyCode.A);
				else if (action.getContent().equals("down"))
					player1.eventPressed(KeyCode.S);
				else if (action.getContent().equals("right"))
					player1.eventPressed(KeyCode.D);
				break;
			case PLAYER_STOP:
				if (action.getContent().equals("up"))
					player1.eventReleased(KeyCode.W);
				else if (action.getContent().equals("left"))
					player1.eventReleased(KeyCode.A);
				else if (action.getContent().equals("down"))
					player1.eventReleased(KeyCode.S);
				else if (action.getContent().equals("right"))
					player1.eventReleased(KeyCode.D);
				break;
			default:
				break;
			}
		}
		action = player2.getClient().pollActions();
		if (action != null) {
			
			switch(action.getType()) {
			case PLAYER_MOVE:
				if (action.getContent().equals("up"))
					player2.eventPressed(KeyCode.W);
				else if (action.getContent().equals("left"))
					player2.eventPressed(KeyCode.A);
				else if (action.getContent().equals("down"))
					player2.eventPressed(KeyCode.S);
				else if (action.getContent().equals("right"))
					player2.eventPressed(KeyCode.D);
				break;
			case PLAYER_STOP:
				if (action.getContent().equals("up"))
					player2.eventReleased(KeyCode.W);
				else if (action.getContent().equals("left"))
					player2.eventReleased(KeyCode.A);
				else if (action.getContent().equals("down"))
					player2.eventReleased(KeyCode.S);
				else if (action.getContent().equals("right"))
					player2.eventReleased(KeyCode.D);
				break;
			default:
				break;
			}
		}

		posTimer -= delta;
		if (posTimer <= 0) {
			player1.getClient().notify(new Message(MessageType.PLAYER_OTHER, player2.getCarPlayer().getCoordinate().toString()));
			player1.getClient().notify(new Message(MessageType.PLAYER, player1.getCarPlayer().getCoordinate().toString()));
			player2.getClient().notify(new Message(MessageType.PLAYER_OTHER, player1.getCarPlayer().getCoordinate().toString()));
			player2.getClient().notify(new Message(MessageType.PLAYER, player2.getCarPlayer().getCoordinate().toString()));
			posTimer = 0.3;
		}
		
		spawnTimer -= delta;
		if (spawnTimer <= 0) {
			//ahora se eliminan solos cuando su coordenada en Y llega a la altura de la pantalla + 500
			ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
			int selecGO = random.nextInt(1,5);
			Double x = random.nextDouble(515, 990);
			switch(selecGO) {
				case 1:
					gameObjects.add(new Obstacle(x, -50));
					player1.getClient().notify(new Message(MessageType.OBSTACLE_NEW, String.format("%.3f", x)));
					player2.getClient().notify(new Message(MessageType.OBSTACLE_NEW, String.format("%.3f", x)));
//					sendAllClients(new Message(MessageType.OBSTACLE_NEW, x.toString()));
					break;
				case 2:
					gameObjects.add(new BadDriver(x, -50, Direction.UP));
					player1.getClient().notify(new Message(MessageType.ENEMY_NEW, String.format("%.3f", x)));
					player2.getClient().notify(new Message(MessageType.ENEMY_NEW, String.format("%.3f", x)));
//					sendAllClients(new Message(MessageType.ENEMY_NEW, x.toString()));
					break;
				case 3:
					gameObjects.add(new PowerUp(x, -50.0, 100));
					player1.getClient().notify(new Message(MessageType.ITEM_NEW, String.format("%.3f", x)));
					player2.getClient().notify(new Message(MessageType.ITEM_NEW, String.format("%.3f", x)));
//					sendAllClients(new Message(MessageType.ITEM_NEW, x.toString()));
					break;
				case 4:
					gameObjects.add(new PowerDown(x, -50.0));
					player1.getClient().notify(new Message(MessageType.PDOWN_NEW, String.format("%.3f", x)));
					player2.getClient().notify(new Message(MessageType.PDOWN_NEW, String.format("%.3f", x)));
//					sendAllClients(new Message(MessageType.PDOWN_NEW, x.toString()));
					break;
			}
			
			GOBuilder.add(gameObjects);

			spawnTimer = random.nextDouble(0.5, 1.5);
		}
		
		checkCollisions();
		
//		int alivePlayers = 0;
		
//		for (int i = 0; i < players.size(); i++) {
//			Player current = players.get(i);
//			if (current.isAlive()) {
//				alivePlayers++;
//				winner = "PLAYER " + (i + 1);
//			}
//			if (current.getPoint() >= Config.maxScore) {
//				winnerExists = true;
//				winner = "PLAYER " + (i + 1);//los player podrian tener un nombre eventualmente
//				break;
//			}
//		}
		
//		if (winnerExists) {
//			//
//		}
		
		// aca va cualquier cosa que no se haga en el metodo update()
		// de los updateables
	}
	
	private void sendAllClients(Message message) {
		for (Client client : lobby.getClientsList()) {
			client.notify(message);
		}
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
//
//	public void oneSecondUpdate(double delta) {
//		fps.set(frames - last_fps_frame);
//		last_fps_frame = frames;
//	}
	
//	protected void addTimeEventsAnimationTimer() {
//		gameTimer = new AnimationTimer() {
//			@Override
//			public void handle(long currentNano) {
//				// Update tick
//				update((currentNano - previousNanoFrame) / NANOS_IN_SECOND_D);
//				previousNanoFrame = currentNano;
//	
//				// Update second
//				if (currentNano - previousNanoSecond > NANOS_IN_SECOND) {
//					oneSecondUpdate((currentNano - previousNanoSecond) / NANOS_IN_SECOND_D);
//					previousNanoSecond = currentNano;
//				}
//	
//			}
//		};
//	
//		previousNanoSecond = System.nanoTime();
//		previousNanoFrame = System.nanoTime();
//		gameTimer.start();
//	}
	
	@Override
	public void run() {
		System.out.println("el thread arranco");
		long currentNano;
		while(true) {
			
			currentNano = System.nanoTime();
			// Update tick
			if ((currentNano - previousNanoFrame) > 16666666) {
				update((currentNano - previousNanoFrame) / NANOS_IN_SECOND_D);
				previousNanoFrame = currentNano;
				previousNanoSecond = System.nanoTime();
				previousNanoFrame = System.nanoTime();
			}
		}
	
		// Update second
//		if (currentNano - previousNanoSecond > NANOS_IN_SECOND) {
//			oneSecondUpdate((currentNano - previousNanoSecond) / NANOS_IN_SECOND_D);
//			previousNanoSecond = currentNano;
//		}
	
	}
}
