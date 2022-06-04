package roadfighter.objects;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import roadfighter.interfaces.Collidator;
import roadfighter.interfaces.Collideable;
import roadfighter.interfaces.Renderable;
import roadfighter.utils.GameObjectBuilder;

public class GoodDriver extends Enemy implements Collidator, Renderable{
	
	private static int cantidad = 0;
	private int id;

	private ImageView render;
	private Image sprite;
	
	private final double WIDTH = 70;
	private final double HEIGHT = 160;
	private Rectangle hitbox;
	
	public GoodDriver(double x, double y,Direction d,String image) {
		setCoordinate(new Coordinate(x, y));
		setDirection(d);
		setOriginalCoordinate(new Coordinate(x, y));
		//this.direction = Direction.UP;
		this.setSpeed(250);
		
		initImages(image);
		render = new ImageView(sprite);
		//render.relocate(x - WIDTH / 2, y - HEIGHT / 2);
		
		hitbox = new Rectangle(x - WIDTH / 2, y - HEIGHT / 2, WIDTH, HEIGHT);
		hitbox.setFill(null);
		hitbox.setStroke(Color.FUCHSIA);
	}
	
	private void initImages(String image) {
		sprite = new Image(image,WIDTH,HEIGHT,true,true);
	}
	
	public void update(double delta) {
		double translateX = 0;
		double translateY = 0;
		if (this.getDirection().equals(Direction.RIGHT)) 
			translateX = 200;
		else if (this.getDirection().equals(Direction.LEFT))
			translateX = -200;
		
		translateY = -this.getSpeed();
		
		move(translateX * delta, translateY * delta);
		
		render.setTranslateX(getCoordinate().getX() - WIDTH / 2);
		render.setTranslateY(getCoordinate().getY() - HEIGHT / 2);
		hitbox.setX(this.getCoordinate().getX() - WIDTH / 2);
		hitbox.setY(this.getCoordinate().getY() - HEIGHT / 2);
	
		if (this.getCoordinate().getY() >= bottomLimit) {
			GameObjectBuilder.getInstance().remove(this);
		}
	}
	

	@Override
	public Shape getCollider() {
		return hitbox;
	}

	@Override
	public void effectPlayer(CarPlayer source) {

		source.getExplosionAudio().play();
	
		GameObjectBuilder builder = GameObjectBuilder.getInstance();
		builder.remove(this);
		
	}
	
	@Override
	public void destroy() {
		
		
		/*GameObjectBuilder g = GameObjectBuilder.getInstance();
		g.remove(this);*/
	}
	
	@Override
	public Node getRender() {
		return render;
	}

	@Override
	public void collide(Collideable collideable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void effectEnemy(GoodDriver source) {
		// TODO Auto-generated method stub
		
	}

}
