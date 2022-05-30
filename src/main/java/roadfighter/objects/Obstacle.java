package roadfighter.objects;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Obstacle extends Item{
	
	// si hay varios obstaculos diferentes habria que hacer mas clases
	// o meterle mas cosas al constructor para que tengan diferentes sprites/hitboxes
	private final double WIDTH = 25;
	private final double HEIGHT = 25;
	
	private Rectangle hitbox;
	private Image sprite;
	private ImageView render;

	public Obstacle(double x, double y) {
		visible = true;
		initImages();
		setCoordinate(new Coordinate(x, y));
		render = new ImageView(sprite);
		render.relocate(x - WIDTH / 2, y - HEIGHT / 2);
		
		hitbox = new Rectangle(x - WIDTH / 2, y - HEIGHT / 2, WIDTH, HEIGHT);
		hitbox.setFill(null);
		hitbox.setStroke(Color.FUCHSIA);
	}
	
	private void initImages() {
		//si tiene animacion supongo que va aca
		sprite = new Image("file:src/resources/images/ObstacleSprite.png");
	}

	@Override
	public void effectPlayer(CarPlayer cp) {
		System.out.println("chocado");
		cp.changeSpeed(0,Action.STOP);
	}
	
	@Override
	public Shape getCollider() {
		return hitbox;
	}
	
	@Override
	public Node getRender() {
		return render;
	}
	
	@Override
	public void destroy() {
		// capas haya que cortar audio
	}
}
