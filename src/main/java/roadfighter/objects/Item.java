package roadfighter.objects;

import roadfighter.interfaces.Collideable;
import roadfighter.interfaces.Renderable;
import roadfighter.interfaces.Updatable;

public abstract class Item extends MapObject implements Collideable, Renderable, Updatable{
	
	protected boolean visible;
	public abstract void effectPlayer(CarPlayer cp);
	
	public boolean isVisible() {
		return visible;
	}
}
