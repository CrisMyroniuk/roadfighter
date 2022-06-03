package roadfighter.objects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import roadfighter.interfaces.Collideable;
import roadfighter.objects_menu.PointsText;
import roadfighter.utils.GameObject;
import roadfighter.utils.GameObjectBuilder;

public abstract class DeathLimit extends GameObject implements Collideable{

	protected final Rectangle hitbox;
	
	public DeathLimit(double x, double y, double width, double height) {
		hitbox = new Rectangle(x, y, width, height);
		hitbox.setFill(null);
		hitbox.setStroke(Color.FUCHSIA);
	}
	
	@Override
	public void effectPlayer(CarPlayer source) {
		GameObjectBuilder builder = GameObjectBuilder.getInstance();
		builder.add(new PointsText((int)hitbox.getX(), new Coordinate(500, 500)));
		source.die();
	}

	@Override
	public void effectEnemy(GoodDriver source) {
		// TODO Auto-generated method stub
		
	}
	
	public Shape getCollider() {
		return hitbox;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
