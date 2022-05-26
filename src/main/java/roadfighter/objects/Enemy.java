package roadfighter.objects;


public abstract class Enemy extends Vehicle{
	private Direction direction;
	private boolean visible;
	
	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public void changeState() {
		setVisible(false);
	}

	@Override
	public void move(double x, double y) {
		// TODO Auto-generated method stub
		switch (direction) {
		case UP: {
			getCoordinate().setY(getCoordinate().getY()+y);
			break;
		}
		case DOWN:{
			getCoordinate().setY(getCoordinate().getY()-y);
			break;
		}
		case LEFT:{
			getCoordinate().setX(getCoordinate().getX()-x);
			break;
		}
		case RIGHT:{
			getCoordinate().setX(getCoordinate().getX()-y);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + direction);
		}
	}
}
