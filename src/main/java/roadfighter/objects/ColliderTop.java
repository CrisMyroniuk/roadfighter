package roadfighter.objects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class ColliderTop extends Item{
	
	// si hay varios obstaculos diferentes habria que hacer mas clases
	// o meterle mas cosas al constructor para que tengan diferentes sprites/hitboxes
	private final double WIDTH = 500;
	private final double HEIGHT = 25;
	
	private Rectangle hitbox;

	public ColliderTop(double x, double y) {
		visible = true;
		setCoordinate(new Coordinate(x, y));
		
		hitbox = new Rectangle(500,-200, WIDTH, HEIGHT);
		hitbox.setFill(Color.FUCHSIA);
		hitbox.setStroke(Color.FUCHSIA);
	}
	
	@Override
	public Shape getCollider() {
		return hitbox;
	}
	
	@Override
	public void destroy() {}

	@Override
	public void effectPlayer(CarPlayer cp) {
		cp.setCoordinate(new Coordinate(cp.getOriginalCoordinate().getX(),cp.getOriginalCoordinate().getY()));
	}
	
	@Override
	public void effectEnemy(GoodDriver e) {
		e.setCoordinate(new Coordinate(e.getOriginalCoordinate().getX(),e.getOriginalCoordinate().getY()));
	}

	@Override
	public void update(double deltaTime) {}
}
