package roadfighter.objects;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import roadfighter.Config;
import roadfighter.utils.GameObjectBuilder;

public class BadDriver extends Enemy {
	
	private static int cantidad = 0;
	private int id;
	
	private Coordinate objective;
	private double timer;
	
	private final int WIDTH = 70;
	private final int HEIGHT = 160;
	
	private Random random = new Random();
	
	public BadDriver(double x, double y, Direction d) {
		setCoordinate(new Coordinate(x, y));
		setDirection(d);
		this.setSpeed(Config.roadSpeed - Config.enemySpeed);

		objective = this.getCoordinate();
		timer = 3;
		
		initImages();
		render = new ImageView(sprite);
		
		hitbox = new Rectangle(x - WIDTH / 2, y - HEIGHT / 2, WIDTH, HEIGHT);
		hitbox.setFill(null);
		hitbox.setStroke(Color.FUCHSIA);
		
		id = cantidad++;
	}
	
	private void initImages() {
		ArrayList<String> imagenesDrivers = new ArrayList<String>();
		imagenesDrivers.add("file:src/resources/images/Enemy1.png");
		imagenesDrivers.add("file:src/resources/images/Enemy2.png");
		imagenesDrivers.add("file:src/resources/images/Enemy3.png");
		imagenesDrivers.add("file:src/resources/images/Enemy4.png");
		sprite = new Image(imagenesDrivers.get(random.nextInt(0,4)), WIDTH, HEIGHT, true, true);
	}
	
	public void obstructPath(CarPlayer player) {
		if (timer > 0) {
			objective = new Coordinate(
					player.getCoordinate().getX(),
					this.getCoordinate().getY()
					); // acceder solo a las cordenadas no al player completo
		}
	}
	
	@Override
	public void update(double delta) {
		super.update(delta);
		//moveTowardsObjective(delta);
		if (this.getCoordinate().getY() >= bottomLimit) {
			GameObjectBuilder.getInstance().remove(this);
		}
	}
	
	@Override
	public void destroy() {
		System.out.println("bad destruido: " + id);
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
	
	@Override
	public Shape getCollider() {
		return hitbox;
	}
	
	@Override
	public ImageView getRender() {
		return render;
	}

	@Override
	public void effectPlayer(CarPlayer source) {
		
		source.getExplosionAudio().play();
		
		GameObjectBuilder builder = GameObjectBuilder.getInstance();
		builder.remove(this);
		
		source.changeStateDeath();
	}

	@Override
	public void effectEnemy(GoodDriver source) {
		// TODO Auto-generated method stub
		
	}
	
	
}
