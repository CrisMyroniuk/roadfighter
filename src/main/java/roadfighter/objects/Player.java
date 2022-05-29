package roadfighter.objects;

import roadfighter.utils.GameObject;

public class Player extends GameObject{

	private Integer points;
	private PlayerState state;
	private CarPlayer car;

	public Player (CarPlayer cp) {
		car=cp;
		state=state.PLAYER_LIVE;
		points=0;
	}
	
	public Integer getPoint() {
		return points;
	}
	
	public CarPlayer getCarPlayer() {
		return car;
	}

	public void setPoint(Integer point) {
		this.points = point;
	}
	
	public void addPoints(int p) {
		setPoint(getPoint() + p);
	}

	public void removePoints(int p) {
		setPoint(getPoint() - p);
	}
	
	//region Metodos
	
//	public void setDireccion(Direction d) {
//		switch (d) {
//		case RIGHT: {
//			car.setDirectionRight();
//			break;
//		}
//		case LEFT: {
//			car.setDirectionLeft();
//			break;
//		}
//		case UP: {
//			car.setDirectionUp();
//			car.changeSpeed(10, Action.SPEED_UP);
//			break;
//		}
//		case DOWN: {
//			car.setDirectionDown();
//			car.changeSpeed(10, Action.SPEED_DOWN);
//			break;
//		}
//		default:
//			throw new IllegalArgumentException("Unexpected value: " + d);
//		}
//	}
	
	public Coordinate myCoord() {
		return car.getCoordinate();
	}
	
	public void changeStateDeath() {
		
		this.state = PlayerState.PLAYER_DEATH;
		
	}
	
	public void changeStateWin() {
		
		this.state = PlayerState.PLAYER_WINS;
		
	}
	
	public PlayerState getState() {
		return state;
	}

	@Override
	public void destroy() {
		// TODO lo que sea que se tenga que desalocar del jugador
		// supongo que eventualmente podria ir si se cierra alguna conexion
		
	}
	
	//endregion
	
	/*private void addInputEvents() {
		currentScene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				switch (e.getCode()) {
				case RIGHT:
				case D:
					mario.setDirectionRight(true);
					break;
				case LEFT:
				case A:
					mario.setDirectionLeft(true);
					break;
				case Q:
					mario.die();
					break;
				default:
					break;
				}
			}
		});*/
	
}
