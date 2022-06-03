package roadfighter.objects_menu;

import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import roadfighter.interfaces.Updatable;
import roadfighter.objects.Coordinate;
import roadfighter.utils.GameObjectBuilder;

public class PointsText extends GenericText implements Updatable{

	private final TranslateTransition animation;
	private final Duration translateDuration = Duration.millis(1000);
	private Coordinate position;
	private double timer = 1;
	
	public PointsText(int points, Coordinate coordinate) {
		super(Integer.toString(points), coordinate);
		position = coordinate;
		
		animation = initAnimation();
	}
	
	private TranslateTransition initAnimation() {
		TranslateTransition translateTransition = new TranslateTransition(translateDuration, render);
		translateTransition.setCycleCount(1);
		translateTransition.setFromY(position.getY() + 25);
		translateTransition.setToY(position.getY() - 70);
		translateTransition.play();
		return translateTransition;
	}
	
	public void update(double delta) {
		timer -= delta;
		if (timer <= 0) {
			GameObjectBuilder.getInstance().remove(this);
		}
	}
	
	public void destroy() {
		animation.stop();
	}
}
