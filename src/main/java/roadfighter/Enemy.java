package roadfighter;

import org.hamcrest.CoreMatchers;

public class Enemy extends Vehicle{
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
	
	
	public Enemy(double x, double y,Direction d) {
		super();
		this.setDirection(d);
		this.setVisible(true);
		setCoordinate(new Coordinate(x, y));
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
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + direction);
		}
	}
}
