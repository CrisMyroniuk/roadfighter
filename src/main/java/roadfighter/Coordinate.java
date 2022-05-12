package roadfighter;

public class Coordinate {

	private double x;
	private double y;
	
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public Coordinate(double x, double y) {
		// TODO Auto-generated constructor stub
		setX(x);
		setY(y);
	}

	public boolean intersection(Coordinate c) {
		if(getX()==c.getX())
			if(getY()==c.getY())
				return true;
		return false;
	}
}
