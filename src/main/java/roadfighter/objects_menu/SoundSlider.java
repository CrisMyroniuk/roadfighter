package roadfighter.objects_menu;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.HBox;
import roadfighter.interfaces.Renderable;
import roadfighter.utils.GameObject;

public abstract class SoundSlider extends GameObject implements Renderable{
	
	private Slider slider;
	private Label label;
	private HBox render;
	EventHandler<DragEvent> onStartDrag;
	EventHandler<DragEvent> onEndDrag;
	
	public SoundSlider(String text, double x, double y) {
		slider = new Slider(0.0, 1.0, 0.5);
		label = new Label(text);
		render = new HBox(label, slider);
		render.setTranslateX(x);
		render.setTranslateY(y);
		
		label.setStyle(
				" -fx-background-color: \n"
				+ "		        linear-gradient(#ffd65b, #e68400),\n"
				+ "		        linear-gradient(#ffef84, #f2ba44),\n"
				+ "		        linear-gradient(#ffea6a, #efaa22),\n"
				+ "		        linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\n"
				+ "		        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));"
				+ "-fx-background-radius: 30;"
				+"-fx-background-insets: 0,1,2,3,0;"
				+"-fx-text-fill: #654b00;"
				+"-fx-font-weight: bold;"
				+"-fx-font-size: 15px;" 
				+"-fx-padding: 10 50 10 50");
		slider.setStyle(
				" -fx-background-color: \n"
				+ "		        linear-gradient(#ffd65b, #e68400),\n"
				+ "		        linear-gradient(#ffef84, #f2ba44),\n"
				+ "		        linear-gradient(#ffea6a, #efaa22),\n"
				+ "		        linear-gradient(#ffe657 0%, #f8c202 50%, #eea10b 100%),\n"
				+ "		        linear-gradient(from 0% 0% to 15% 50%, rgba(255,255,255,0.9), rgba(255,255,255,0));"
				+ "-fx-background-radius: 30;"
				+"-fx-background-insets: 0,1,2,3,0;"
				+"-fx-padding: 10 50 10 50"
				);
		
		addListeners();
	}
	
	private void addListeners() {
		slider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				changeAsociatedValue((double)newValue);
			}
			
		});
	}
	
	protected abstract void changeAsociatedValue(double newValue);
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public Node getRender() {
		return render;
	}

}
