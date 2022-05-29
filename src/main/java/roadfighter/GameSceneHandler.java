package roadfighter;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import roadfighter.objects.CarPlayer;
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
	
	private EventHandler<KeyEvent> keyReleasedHandler;
	
	public GameSceneHandler(RoadFighterGame g) {
		super(g);
	}
	
	public void load(boolean fullStart) {
		Group rootGroup = new Group();
		scene.setRoot(rootGroup);

		car = new CarPlayer(50.0, 500.0);
		player = new Player(car);
		
		GameObjectBuilder GOBuilder = GameObjectBuilder.getInstance();
		GOBuilder.setRootNode(rootGroup);
		GOBuilder.add(player, car);
		
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
		//aca va cualquier cosa que no se haga en el metodo update()
		//de los updateables
	}

}
