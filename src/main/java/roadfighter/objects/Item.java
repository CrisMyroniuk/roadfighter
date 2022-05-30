package roadfighter.objects;

import roadfighter.interfaces.Collideable;
import roadfighter.interfaces.Renderable;

public abstract class Item extends MapObject implements Collideable, Renderable{
	
	protected boolean visible;
	public abstract void effectPlayer(CarPlayer cp);
	
	public boolean isVisible() {
		return visible;
	}
}
