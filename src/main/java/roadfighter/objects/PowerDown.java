package roadfighter.objects;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import roadfighter.Config;
import roadfighter.utils.GameObjectBuilder;

public class PowerDown extends Item{

	private final double speedDown = 100;
	private final double WIDTH = 40;
	private final double HEIGHT = 40;
	
	public double getSpeedDown() {
		return speedDown;
	}
	
	public PowerDown(double x, double y) {
		setCoordinate(new Coordinate(x, y));
		visible=true;
		
		hitbox = new Rectangle(x, y, WIDTH, HEIGHT);
		hitbox.setFill(null);
		hitbox.setStroke(Color.TRANSPARENT);
	}

	@Override
	public void effectPlayer(CarPlayer cp) {
		// Baja la velocidad del auto
		cp.changeSpeed(0,Action.STOP);
		cp.changeSpeed(100,Action.SPEED_UP);
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
