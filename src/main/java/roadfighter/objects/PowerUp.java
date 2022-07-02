package roadfighter.objects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import roadfighter.Config;
import roadfighter.utils.GameObjectBuilder;

public class PowerUp extends Item{

	private int points;
	private final double WIDTH = 70;
	private final double HEIGHT = 70;
	
	public PowerUp(double x, double y, int p) {
		setCoordinate(new Coordinate(x,y));
		setModel(null);
		setPoints(p);
		visible=true;
		
		hitbox = new Rectangle(x, y, WIDTH, HEIGHT);
		hitbox.setFill(null);
		hitbox.setStroke(Color.MEDIUMSEAGREEN);
	}
	
	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public void effectPlayer(CarPlayer cp) {
		// TODO Agregamos los puntos al jugador
		cp.addPoints(points);
		GameObjectBuilder builder = GameObjectBuilder.getInstance();
		builder.remove(this);
	}
	
	public void desaparecer() {
		//To do llamar a que haga alguna animacion y/o sonido
		visible = false;
	}
	
	@Override
	public void destroy() {
		//cortar audio
	}

	@Override
	public Shape getCollider() {
		return hitbox;
	}

	@Override
	public void effectEnemy(GoodDriver source) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(double delta) {
		getCoordinate().setY(getCoordinate().getY() + Config.roadSpeed * delta);
		
		hitbox.setX(this.getCoordinate().getX() - WIDTH / 2);
		hitbox.setY(this.getCoordinate().getY() - HEIGHT / 2);
		
		if (this.getCoordinate().getY() >= bottomLimit) {
			GameObjectBuilder.getInstance().remove(this);
		}
	}

}
