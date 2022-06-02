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
	private double posY = 0;

//	private final int cityWidth = 500;
//	private final int cityHeight = 795;
	private final int forestWidth=1500;
	private final int forestHeight=1000;
//	private final int roadWidth=750;
//	private final int roadHeight=1000;
	private Rectangle colliderTop;
	public Background() {
		Image backgroundImage = new Image("file:src/resources/images/background.png", forestWidth, forestHeight, false, false);
		ImagePattern image_pattern = new ImagePattern(backgroundImage, forestWidth, forestHeight,forestWidth, forestHeight,false);
		Rectangle road = new Rectangle(forestWidth, forestHeight*2);
		
		road.setFill(image_pattern);
//		colliderTop = new Rectangle(40,40,30,795);
//		colliderTop.setFill(null);
//		colliderTop.setStroke(Color.FUCHSIA);
		render = new VBox(road);
		// TODO zIndex list
		render.setViewOrder(10);
	}
	public Node getRender() {
		return render;
	}

	public void update(double deltaTime) {
		posY += 250 * deltaTime;
		render.setTranslateY((posY % forestHeight) -1000 );
		
	}

	@Override
	public void destroy() {}

}
