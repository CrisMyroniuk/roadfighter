package roadfighter;

public class GoodDriver extends Enemy{

	public GoodDriver(double x, double y,Direction d) {
		this.setDirection(d);
		this.setVisible(true);
		setCoordinate(new Coordinate(x, y));
	}
	
	
}
