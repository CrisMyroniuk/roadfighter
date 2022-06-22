package roadfighter.objects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import roadfighter.Config;
import roadfighter.utils.GameObjectBuilder;

public class Obstacle extends Item{
	
	// si hay varios obstaculos diferentes habria que hacer mas clases
	// o meterle mas cosas al constructor para que tengan diferentes sprites/hitboxes
	private final double WIDTH = 50;
	private final double HEIGHT = 50;

	public Obstacle(double x, double y,String path) {
		visible = true;
		setCoordinate(new Coordinate(x, y));
		//render.relocate(x - WIDTH / 2, y - HEIGHT / 2);
		
		hitbox = new Rectangle(x - WIDTH / 2, y - HEIGHT / 2, WIDTH, HEIGHT);
		hitbox.setFill(null);
		hitbox.setStroke(Color.FUCHSIA);
	}
	
	@Override
	public void update(double delta) {
		//getCoordinate().setX(getCoordinate().getX() + x);
		getCoordinate().setY(getCoordinate().getY() + Config.roadSpeed * delta);
		
		hitbox.setX(this.getCoordinate().getX() - WIDTH / 2);
		hitbox.setY(this.getCoordinate().getY() - HEIGHT / 2);
		
		if (this.getCoordinate().getY() >= bottomLimit) {
			GameObjectBuilder.getInstance().remove(this);
		}
	
	}

	@Override
	public void effectPlayer(CarPlayer cp) {
		if (this.getCoordinate().getY() + this.WIDTH <= cp.getCoordinate().getY())
			cp.move(0, 5);
		
		GameObjectBuilder builder = GameObjectBuilder.getInstance();
		builder.remove(this);
		
		if(cp.getPoint()>0) {

			cp.removePoints(80);
			if(cp.getPoint()<0) {
				cp.setPoint(0);
			}
		}
	}
	
	
	@Override
	public Shape getCollider() {
		return hitbox;
	}
	
	@Override
	public void destroy() {
		
	}

	@Override
	public void effectEnemy(GoodDriver source) {
		// TODO Auto-generated method stub
		
	}
}
