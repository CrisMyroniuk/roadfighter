package roadfighter.objects_menu;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import roadfighter.interfaces.Renderable;
import roadfighter.utils.GameObject;

public class MenuTextField extends GameObject implements Renderable{
	
	private HBox render;
	private Label label;
	private TextField textField;
	
	public MenuTextField(String text, double x, double y) {
		textField = new TextField();
		label = new Label(text);
		render = new HBox(label, textField);
		render.setTranslateX(x);
		render.setTranslateY(y);
	}
	
	public String getText() {
		return textField.getText();
	}

	@Override
	public Node getRender() {
		return render;
	}

	@Override
	public void destroy() {}
	
}
