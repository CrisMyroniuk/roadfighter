package roadfighter.objects;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import roadfighter.interfaces.Renderable;
import roadfighter.interfaces.Updatable;
import roadfighter.utils.GameObject;

public class Background extends GameObject implements Updatable, Renderable{

	private VBox render;
	private double posX = 0;

	private final int cityWidth = 500;
	private final int cityHeight = 795;
	private Rectangle colliderTop;
	public Background() {
		Image backgroundImage = new Image("file:background.png", cityWidth, cityHeight, false, false);
		ImagePattern image_pattern = new ImagePattern(backgroundImage, cityWidth, cityHeight, cityWidth, cityHeight,
				false);
		Rectangle city = new Rectangle(cityWidth, 795 + cityHeight);
		
		city.setFill(image_pattern);
		colliderTop = new Rectangle(40,40,30,795);
		colliderTop.setFill(null);
		colliderTop.setStroke(Color.FUCHSIA);
		render = new VBox(city);
		// TODO zIndex list
		render.setViewOrder(10);
	}
	public Node getRender() {
		return render;
	}

	public void update(double deltaTime) {
		posX += 250 * deltaTime;
		render.setTranslateY((posX % cityHeight) - 795);
		
	}

	@Override
	public void destroy() {}

}
