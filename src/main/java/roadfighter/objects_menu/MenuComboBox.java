package roadfighter.objects_menu;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import roadfighter.interfaces.Renderable;
import roadfighter.utils.GameObject;

public class MenuComboBox extends GameObject implements Renderable{

	private HBox render;
	private Label label;
	private ComboBox<Integer> comboBox;
	
	public MenuComboBox(String text, double x, double y) {
		label = new Label(text);
		comboBox = new ComboBox<Integer>();
		render = new HBox(label, comboBox);
		render.setTranslateX(x);
		render.setTranslateY(y);
	}
	
	public void addItem(Integer item) {
		comboBox.getItems().add(item);
	}
	
	public Integer getValue() {
		return comboBox.getValue();
	}
	
	@Override
	public Node getRender() {
		return render;
	}

	@Override
	public void destroy() {}
	
}
