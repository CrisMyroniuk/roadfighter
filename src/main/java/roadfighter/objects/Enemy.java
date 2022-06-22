package roadfighter.objects;


import javafx.scene.shape.Rectangle;
import roadfighter.Config;

public abstract class Enemy extends Vehicle{
	private Direction direction;
	private boolean visible;
	
	protected int WIDTH;
	protected int HEIGHT;
	
	protected Rectangle hitbox;
	protected final double bottomLimit = Config.baseHeight + 500;
	
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
	
	@Override
	public void update(double delta) {
		double translateY = this.getSpeed() * delta;
		
		move(0, translateY);
		
		hitbox.setX(this.getCoordinate().getX() - WIDTH / 2);
		hitbox.setY(this.getCoordinate().getY() - HEIGHT / 2);
	}
}
