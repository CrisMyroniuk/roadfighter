package roadfighter.objects;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import roadfighter.objects_menu.PointsText;
import roadfighter.utils.GameObjectBuilder;

public class PowerDown extends Item{

	private final double speedDown = 100;
	private final double WIDTH = 40;
	private final double HEIGHT = 40;
	
	private Image sprite;
	private ImageView render;
	
	public double getSpeedDown() {
		return speedDown;
	}
	
	public PowerDown(double x, double y,String path) {
		setCoordinate(new Coordinate(x, y));
		visible=true;
		
		hitbox = new Rectangle(x, y, WIDTH, HEIGHT);
		hitbox.setFill(null);
		hitbox.setStroke(Color.TRANSPARENT);
		
		initImages(path);
		render = new ImageView(sprite);
		render.setTranslateX(getCoordinate().getX() - WIDTH / 2);
		render.setTranslateY(getCoordinate().getY() - HEIGHT / 2);
	}
	
	private void initImages(String path) {
		sprite = new Image(path,WIDTH,HEIGHT,true,true);//place holder
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
	public Node getRender() {
		return render;
	}

	@Override
	public void effectEnemy(GoodDriver source) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(double delta) {
		getCoordinate().setY(getCoordinate().getY() + 150 * delta);
		
		render.setTranslateX(getCoordinate().getX() - WIDTH / 2);
		render.setTranslateY(getCoordinate().getY() - HEIGHT / 2);
		hitbox.setX(this.getCoordinate().getX() - WIDTH / 2);
		hitbox.setY(this.getCoordinate().getY() - HEIGHT / 2);
		
		if (this.getCoordinate().getY() >= bottomLimit) {
			GameObjectBuilder.getInstance().remove(this);
		}
	}

}
