package roadfighter.objects;

import javafx.scene.shape.Rectangle;
import roadfighter.Config;
import roadfighter.interfaces.Collideable;
import roadfighter.interfaces.Updatable;

public abstract class Item extends MapObject implements Collideable, Updatable{
	
	protected Rectangle hitbox;
	protected boolean visible;
	protected final double bottomLimit = Config.baseHeight + 500;
	
	public abstract void effectPlayer(CarPlayer cp);
	
	public boolean isVisible() {
		return visible;
	}
}
