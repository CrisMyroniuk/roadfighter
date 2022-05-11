package roadfighter;

public abstract class Item extends MapObject{
	
	boolean visible;
	public abstract void effectPlayer(CarPlayer cp);
}
