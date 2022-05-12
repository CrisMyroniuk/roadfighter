package roadfighter;

public class PowerUp extends Item{

	int points;
	
	
	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public PowerUp(int x, int y,double h, double w,int p) {
		setCoordinate(new Coordinate(x,y));
		setHeight(h);
		setWidth(w);
		setModel(null);
		setPoints(p);
		visible=true;
	}

	@Override
	public void effectPlayer(CarPlayer cp) {
		// TODO Agregamos los puntos al jugador
		cp.addPoints(points);
	}

}
