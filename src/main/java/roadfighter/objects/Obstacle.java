package roadfighter.objects;


import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import roadfighter.objects_menu.PointsText;
import roadfighter.utils.GameObjectBuilder;

public class Obstacle extends Item{
	
	// si hay varios obstaculos diferentes habria que hacer mas clases
	// o meterle mas cosas al constructor para que tengan diferentes sprites/hitboxes
	private final double WIDTH = 50;
	private final double HEIGHT = 50;
	
	private Image sprite;
	private ImageView render;
	private MediaPlayer mediaPlayer;

	public Obstacle(double x, double y,String path) {
		visible = true;
		initImages(path);
		setCoordinate(new Coordinate(x, y));
		render = new ImageView(sprite);
		//render.relocate(x - WIDTH / 2, y - HEIGHT / 2);
		
		hitbox = new Rectangle(x - WIDTH / 2, y - HEIGHT / 2, WIDTH, HEIGHT);
		hitbox.setFill(null);
		hitbox.setStroke(Color.FUCHSIA);
		
		render.setTranslateX(getCoordinate().getX() - WIDTH / 2);
		render.setTranslateY(getCoordinate().getY() - HEIGHT / 2);
	}
	
	private void initImages(String path) {
		//si tiene animacion supongo que va aca
		sprite = new Image(path,WIDTH,HEIGHT,true,true);
	}
	
	@Override
	public void update(double delta) {
		//getCoordinate().setX(getCoordinate().getX() + x);
		getCoordinate().setY(getCoordinate().getY() + 150 * delta);
		
		render.setTranslateX(getCoordinate().getX() - WIDTH / 2);
		render.setTranslateY(getCoordinate().getY() - HEIGHT / 2);
		hitbox.setX(this.getCoordinate().getX() - WIDTH / 2);
		hitbox.setY(this.getCoordinate().getY() - HEIGHT / 2);
		
		if (this.getCoordinate().getY() >= bottomLimit) {
			GameObjectBuilder.getInstance().remove(this);
		}
	
	}

	@Override
	public void effectPlayer(CarPlayer cp) {
		if (this.getCoordinate().getY() + this.WIDTH <= cp.getCoordinate().getY())
			cp.move(0, 5);
		//cp.changeSpeed(0,Action.STOP);
		System.out.println("chocado");
		cp.getExplosionAudio().play();
		cp.changeSpeed(0,Action.STOP);
		cp.move(0, 5);
		cp.changeSpeed(100,Action.SPEED_UP);
		
		GameObjectBuilder builder = GameObjectBuilder.getInstance();
		builder.remove(this);
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
		
	}

	@Override
	public void effectEnemy(GoodDriver source) {
		// TODO Auto-generated method stub
		
	}
}
