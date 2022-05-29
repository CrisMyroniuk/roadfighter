package roadfighter.objects;

public class BadDriver extends Enemy {
	
	private Coordinate objective;
	private double timer;
	
	public BadDriver(double x, double y, Direction d) {
		setCoordinate(new Coordinate(x, y));
		setDirection(d);
		objective = new Coordinate(x, y);
		timer = 3;
	}
	
	public void obstructPath(CarPlayer player) {
		if (timer > 0) {
			objective = new Coordinate(
					player.getCoordinate().getX(),
					this.getCoordinate().getY()
					); // acceder solo a las cordenadas no al player completo
		}
	}
	
	public void moveTowardsObjective(double deltaTime) {
		// deltaTime funciona como si estuviera en segundos
		// pero capas hay que cambiarlo para que funcione con nano o millis
		if (timer > 0) {
			double vectorX = objective.getX() - this.getCoordinate().getX();
			double vectorY = objective.getY() - this.getCoordinate().getY();
			
			Coordinate normalizedVector = new Coordinate(vectorX, vectorY).normalize();
			double step = this.getSpeed() * deltaTime;
			
			// cada vez que se mueve resta el tiempo del timer
			timer -= deltaTime;
			
			// aca no use this.move() porque solo lo mueve en Y y no se si sobreescribir el metodo
			this.setCoordinate(new Coordinate(
					this.getCoordinate().getX() + normalizedVector.getX() * step,
					this.getCoordinate().getY() + normalizedVector.getY() * step
					));
		}
	}
	
	public boolean hasObstructed() {
		return timer <= 0;
	}
	
	public void update(double delta) {
		moveTowardsObjective(delta);
	}
}
