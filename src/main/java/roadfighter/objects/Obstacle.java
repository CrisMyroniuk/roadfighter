package roadfighter.objects;

import java.io.File;
import java.nio.file.Paths;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;
import roadfighter.utils.GameObjectBuilder;

public class Obstacle extends Item{
	
	// si hay varios obstaculos diferentes habria que hacer mas clases
	// o meterle mas cosas al constructor para que tengan diferentes sprites/hitboxes
	private final double WIDTH = 25;
	private final double HEIGHT = 25;
	
	private Rectangle hitbox;
	private Image sprite;
	private ImageView render;
	private MediaPlayer mediaPlayer;

	public Obstacle(double x, double y) {
		visible = true;
		initImages();
		setCoordinate(new Coordinate(x, y));
		render = new ImageView(sprite);
		//render.relocate(x - WIDTH / 2, y - HEIGHT / 2);
		
		hitbox = new Rectangle(x - WIDTH / 2, y - HEIGHT / 2, WIDTH, HEIGHT);
		hitbox.setFill(null);
		hitbox.setStroke(Color.FUCHSIA);
		
		render.setTranslateX(getCoordinate().getX() - WIDTH / 2);
		render.setTranslateY(getCoordinate().getY() - HEIGHT / 2);
	}
	
	private void initImages() {
		//si tiene animacion supongo que va aca
		sprite = new Image("file:src/resources/images/ObstacleSprite.png");
	}
	
	@Override
	public void update(double delta) {
		//getCoordinate().setX(getCoordinate().getX() + x);
		getCoordinate().setY(getCoordinate().getY() + 150 * delta);
		
		render.setTranslateX(getCoordinate().getX() - WIDTH / 2);
		render.setTranslateY(getCoordinate().getY() - HEIGHT / 2);
		hitbox.setX(this.getCoordinate().getX() - WIDTH / 2);
		hitbox.setY(this.getCoordinate().getY() - HEIGHT / 2);
	
	}

	@Override
	public void effectPlayer(CarPlayer cp) {
		System.out.println("chocado");
		
		//por alguna razon si meto la explosion en una carpeta, da error la ruta.

		String src = "file:src/resources/sound/explosion.mp3";
		AudioClip audioClip = new AudioClip(src);
		audioClip.setVolume(0.6);
		audioClip.play();
		cp.changeSpeed(0,Action.STOP);
		cp.move(0, 5);
		cp.changeSpeed(100,Action.SPEED_UP);
		destroy();
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
		/*GameObjectBuilder g = GameObjectBuilder.getInstance();
		g.remove(this);*/
	}

	@Override
	public void effectEnemy(GoodDriver source) {
		// TODO Auto-generated method stub
		
	}
}
