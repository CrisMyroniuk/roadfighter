package roadfighter.objects;

public class GoodDriver extends Enemy{

	public GoodDriver(double x, double y,Direction d) {
		this.setDirection(d);
		this.setVisible(true);
		setCoordinate(new Coordinate(x, y));
	}
	
	public void update(double delta) {
		
	}
}
