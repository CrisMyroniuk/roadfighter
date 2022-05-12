package roadfighter;

public class Enemy extends Vehicle{
	private Enum orientation;
	private boolean visible;
	
	public Enemy(double x, double y) {
		super();
		this.orientation = orientation;
		this.visible = visible;
		this.setX(x);
		this.setY(y);
	}
}

