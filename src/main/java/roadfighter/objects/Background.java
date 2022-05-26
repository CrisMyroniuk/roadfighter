package roadfighter.objects;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import roadfighter.interfaces.Renderable;
import roadfighter.interfaces.Updatable;
import roadfighter.utils.GameObject;

public class Background extends GameObject implements Updatable, Renderable{

	private VBox render;
	private double posX = 0;

	private final int cityWidth = 720;
	private final int cityHeight = 720;
	
	public Background() {
		Image backgroundImage = new Image("file:background.png", cityWidth, cityHeight, false, false);
		ImagePattern image_pattern = new ImagePattern(backgroundImage, cityWidth, cityHeight, cityWidth, cityHeight,
				false);
		Rectangle city = new Rectangle(500 + cityWidth, cityHeight);
		
		city.setFill(image_pattern);
		render = new VBox(city);
		// TODO zIndex list
		render.setViewOrder(10);
	}
	public Node getRender() {
		return render;
	}

	public void update(double deltaTime) {
		posX += -250 * deltaTime * 0.01;
		render.setTranslateX(posX % cityWidth);
		
	}

	@Override
	public void destroy() {}

}
