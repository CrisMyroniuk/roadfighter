package roadfighter.objects;

import java.util.Objects;

public class Coordinate {

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	public boolean equals(Coordinate c) {
		return this.getX() == c.getX()
				&& this.getY() == c.getY();
	}

	private double x;
	private double y;
	
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}
	
	public double getNorm () {
		//la norma como si fuera un vector
		return this.x == 0 && this.y == 0 ? 0 : Math.hypot(x, y);
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
	
	public Coordinate normalize() {
		double norm = this.getNorm();
		if (norm != 0) {
			this.x /= norm;
			this.y /= norm;
		}
		
		return this;
	}

	public boolean intersection(Coordinate c) {
		if(getX()==c.getX())
			if(getY()==c.getY())
				return true;
		return false;
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
