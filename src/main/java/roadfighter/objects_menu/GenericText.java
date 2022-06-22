package roadfighter.objects_menu;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import roadfighter.interfaces.Renderable;
import roadfighter.objects.Coordinate;
import roadfighter.utils.GameObject;

public class GenericText extends GameObject implements Renderable {

	//private final int Y = Config.baseHeight / 3 - 35;
	//private final TranslateTransition idleAnimation;
	//private final Duration translateDuration = Duration.millis(1000);

	private Text text;
	protected VBox render;

	public GenericText(String inicialText, Coordinate coordinate, double size) {
		text = new Text(inicialText);

		render = new VBox(text);
		render.setAlignment(Pos.TOP_CENTER);
		render.setTranslateX(coordinate.getX());
		render.setTranslateY(coordinate.getY());
		// Esto debería heredarse?
		//render.setPrefWidth(Config.baseWidth);

		Font font = Font.font("Verdana", FontWeight.EXTRA_BOLD, size);
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
		/*+"-fx-font-size: 85px;"*/);
		
		//idleAnimation = initIdleAnimation();
	}
	
	public GenericText(String inicialText, Coordinate coordinate) {
		this(inicialText, coordinate, 30);
	}
	
	public GenericText(String inicialText) {
		this(inicialText, new Coordinate(0, 0));
	}
	
	public GenericText() {
		this("");
	}
	
	public GenericText(Coordinate coordinate) {
		this("", coordinate);
	}

	@Override
	public Node getRender() {
		return render;
	}
	
//	private TranslateTransition initIdleAnimation() {
//		TranslateTransition translateTransition = new TranslateTransition(translateDuration, render);
//		translateTransition.setCycleCount(Animation.INDEFINITE);
//		translateTransition.setFromY(Y -10);
//		translateTransition.setToY(Y + 10);
//		translateTransition.setAutoReverse(true);
//		translateTransition.play();
//		return translateTransition;
//	}
	
	public void setText(String newText) {
		text.setText(newText);
	}
	
	public void setCoordinate(Coordinate coordinate) {
		render.setTranslateX(coordinate.getX());
		render.setTranslateY(coordinate.getY());
	}

	@Override
	public void destroy() {
		//idleAnimation.stop();
	}
}
