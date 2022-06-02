package roadfighter.objects;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class ColliderTop extends Item{
	
	// si hay varios obstaculos diferentes habria que hacer mas clases
	// o meterle mas cosas al constructor para que tengan diferentes sprites/hitboxes
	private final double WIDTH = 25;
	private final double HEIGHT = 25;
	
	private Rectangle hitbox;
	private Image sprite;
	private ImageView render;

	public ColliderTop(double x, double y) {
		visible = true;
		setCoordinate(new Coordinate(x, y));
		render = new ImageView(sprite);
		render.relocate(x - WIDTH / 2, y - HEIGHT / 2);
		
		hitbox = new Rectangle(0,-200, 500, 25);
		hitbox.setFill(null);
		hitbox.setStroke(Color.FUCHSIA);
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

	@Override
	public void effectPlayer(CarPlayer cp) {
		cp.setCoordinate(new Coordinate(cp.getOriginalCoordinate().getX(),cp.getOriginalCoordinate().getY()));
		
	}
	
	@Override
	public void effectEnemy(GoodDriver e) {
		System.out.print("HOLA");
		e.setCoordinate(new Coordinate(e.getOriginalCoordinate().getX(),e.getOriginalCoordinate().getY()));
		
	}

	@Override
	public void update(double deltaTime) {
		// TODO Auto-generated method stub
		
	}
}
