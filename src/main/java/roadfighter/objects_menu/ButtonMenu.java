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

public class ButtonMenu extends GameObject implements Renderable {

	private VBox render;
	private Button button;

	public ButtonMenu(String title, double Y) {
		button = new Button(title);
		render = new VBox(button);
		render.setAlignment(Pos.CENTER);
		render.setTranslateY(Y);
		// Esto debería heredarse?
		render.setPrefWidth(Config.baseWidth);

		//nt font = Font.font("Verdana", FontWeight.NORMAL, 40);
		button.setTextAlignment(TextAlignment.CENTER);

		if(title == "1 PLAYER") {
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
					+"-fx-font-size: 30px;" 
					+"-fx-padding: 10 50 10 50"
					);
		}
		else {
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
					+"-fx-font-size: 30px;" 
					+"-fx-padding: 10 40 10 40"
					);
		}
		
		
		
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
