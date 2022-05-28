package roadfighter.objects_menu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import roadfighter.Config;
import roadfighter.interfaces.Renderable;
import roadfighter.utils.GameObject;

public class TextoComenzar extends GameObject implements Renderable {
	private final int Y = Config.baseHeight * 3 / 5;

	private VBox render;
	private Button button;

	public TextoComenzar() {
		button = new Button("PLAY");
		render = new VBox(button);
		render.setAlignment(Pos.TOP_CENTER);
		render.setTranslateY(Y);
		// Esto debería heredarse?
		render.setPrefWidth(Config.baseWidth);

		//nt font = Font.font("Verdana", FontWeight.NORMAL, 40);
		button.setTextAlignment(TextAlignment.CENTER);
		button.setStyle(" -fx-background-color: \n"
				+ "		        linear-gradient(#ffd65b, #e68400),\n"
				+ "		        linear-gradient(#ffef84, #f2ba44),\n"
				+ "		        linear-gradient(#ffea6a, #efaa22),\n"
				+ "		        linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\n"
				+ "		        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));"
				+ "-fx-background-radius: 30;"
				+"-fx-background-insets: 0,1,2,3,0;"
				+"-fx-text-fill: #654b00;"
				+"-fx-font-weight: bold;"
				+"-fx-font-size: 40px;" 
				+"-fx-padding: 10 20 10 20"
				);
		
	}

	@Override
	public Node getRender() {
		return render;
	}

	@Override
	public void destroy() {	}
	
	public void setOnAction(EventHandler<ActionEvent> onPress) {
		button.setOnAction(onPress);
	}
	
	public void removeOnAction(EventHandler<ActionEvent> onPress) {
		button.removeEventFilter(ActionEvent.ACTION, onPress);
	}
	
}
