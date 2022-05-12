package roadfighter;

public abstract class Item extends MapObject{
	
	protected boolean visible;
	public abstract void effectPlayer(CarPlayer cp);
	
	public boolean isVisible() {
		return visible;
	}
}
