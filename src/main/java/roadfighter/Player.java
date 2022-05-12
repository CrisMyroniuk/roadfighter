package roadfighter;

public class Player {
	private CarPlayer car;
	private boolean live;
	
	
	public Player (CarPlayer cp) {
		car=cp;
		live=true;
	}
	
	//region Metodos
	
	public void setDireccion(Direction d) {
		switch (d) {
		case RIGHT: {
			car.setDirectionRight();
		}
		case LEFT: {
			car.setDirectionLeft();
		}
		case UP: {
			car.setDirectionUp();
			car.changeSpeed(10, Action.SPEED_UP);
		}
		case DOWN: {
			car.setDirectionDown();
			car.changeSpeed(10, Action.SPEED_DOWN);
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + d);
		}
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
