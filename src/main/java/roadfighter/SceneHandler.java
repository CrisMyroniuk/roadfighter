package roadfighter;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import roadfighter.utils.GameObjectBuilder;

public abstract class SceneHandler {
	
	protected RoadFighterGame g;
	protected Scene scene;
	
	protected EventHandler<KeyEvent> keyEventHandler;
	protected EventHandler<MouseEvent> mouseEventHandler;
	
	
	public SceneHandler(RoadFighterGame g) {
			this.g = g;
			prepareScene();
			defineEventHandlers();
	}
	
	public Scene getScene() {
		return scene;
	}
	
	protected abstract void prepareScene();
	protected abstract void defineEventHandlers();
		
	protected void removeInputEvents() {
		scene.removeEventHandler(KeyEvent.KEY_PRESSED, keyEventHandler);
		scene.removeEventHandler(MouseEvent.MOUSE_PRESSED, mouseEventHandler);
	}
	
	protected void unload() {
		GameObjectBuilder.getInstance().removeAll();
		//gameTimer.stop();
		removeInputEvents();
	}

}
