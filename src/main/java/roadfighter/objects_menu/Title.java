package roadfighter.objects_menu;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import roadfighter.Config;
import roadfighter.interfaces.Renderable;
import roadfighter.utils.GameObject;

public class Title extends GameObject implements Renderable {
	private final int Y = Config.baseHeight / 3 - 35;
	
	private final TranslateTransition idleAnimation;
	private final Duration translateDuration = Duration.millis(1000);

	private Text text;
	private VBox render;

	public Title() {
		text = new Text("Road Fighter");

		render = new VBox(text);
		render.setAlignment(Pos.TOP_LEFT);
		render.setTranslateY(Y);
		render.setTranslateX(50);
		// Esto debería heredarse?
		render.setPrefWidth(Config.baseWidth);

		Font font = Font.font("Verdana", FontWeight.EXTRA_BOLD, 50);
		text.setTextAlignment(TextAlignment.CENTER);
		text.setFont(font);
		text.setFill(Color.RED);
		
		idleAnimation = initIdleAnimation();
	}

	@Override
	public Node getRender() {
		return render;
	}
	
	private TranslateTransition initIdleAnimation() {
		TranslateTransition translateTransition = new TranslateTransition(translateDuration, render);
		translateTransition.setCycleCount(Animation.INDEFINITE);
		translateTransition.setFromY(Y -10);
		translateTransition.setToY(Y + 10);
		translateTransition.setAutoReverse(true);
		translateTransition.play();
		return translateTransition;
	}

	@Override
	public void destroy() {
		idleAnimation.stop();
	}

}
