package roadfighter.objects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import roadfighter.interfaces.Collideable;
import roadfighter.utils.GameObject;

public abstract class DeathLimit extends GameObject implements Collideable{

	protected final Rectangle hitbox;
	
	public DeathLimit(double x, double y, double width, double height) {
		hitbox = new Rectangle(x, y, width, height);
		hitbox.setFill(null);
		hitbox.setStroke(Color.FUCHSIA);
	}
	
	@Override
	public void effectPlayer(CarPlayer source) {
		
		if(source.getPoint() > 0 && source.canCrash()) {
			source.removePoints(1);
			if(source.getPoint() < 0) {
				source.setPoint(0);
			}
		}
		
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
