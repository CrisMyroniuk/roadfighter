package roadfighter.objects;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import roadfighter.objects_menu.PointsText;
import roadfighter.utils.GameObjectBuilder;

public class PowerUp extends Item{

	private int points;
	private final double WIDTH = 25;
	private final double HEIGHT = 25;
	
	private Image sprite;
	private ImageView render;
	
	public PowerUp(double x, double y, int p,String path) {
		setCoordinate(new Coordinate(x,y));
		setModel(null);
		setPoints(p);
		visible=true;
		
		hitbox = new Rectangle(x, y, WIDTH, HEIGHT);
		hitbox.setFill(null);
		hitbox.setStroke(Color.MEDIUMSEAGREEN);
		
		initImages(path);
		render = new ImageView(sprite);
		render.setTranslateX(getCoordinate().getX() - WIDTH / 2);
		render.setTranslateY(getCoordinate().getY() - HEIGHT / 2);
	}
	
	private void initImages(String path) {
		sprite = new Image(path,WIDTH,HEIGHT,true,true);//place holder
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
		builder.add(new PointsText(points, getCoordinate()));
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
