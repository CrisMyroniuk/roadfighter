package roadfighter.objects_menu;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
		text = new Text("ROAD FIGHTER");

		render = new VBox(text);
		render.setAlignment(Pos.TOP_CENTER);
		render.setTranslateY(Y);
		// Esto debería heredarse?
		render.setPrefWidth(Config.baseWidth);

		Font font = Font.font("Verdana", FontWeight.EXTRA_BOLD, 50);
		text.setTextAlignment(TextAlignment.CENTER);
		text.setFont(font);
		
		text.setStyle(" -fx-fill: \n"
		+ "		        linear-gradient(#ffd65b, #e68400),\n"
		+ "		        linear-gradient(#ffef84, #f2ba44),\n"
		+ "		        linear-gradient(#ffea6a, #efaa22),\n"
		+ "		        linear-gradient(#ffe657 0%, #f8c202 80%, #eea10b 100%),\n"
		+ "		        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));"
		+ "-fx-background-radius: 30;"
		+"-fx-background-insets: 0,1,2,3,0;"
		+"-fx-text-fill: #654b00;"
		+"-fx-font-size: 85px;");
		
		idleAnimation = initIdleAnimation();
	}
	
	public Title(String titleText) {
		this();
		text.setText(titleText);
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
