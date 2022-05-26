package roadfighter;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Group root = new Group();
		Scene currentScene = new Scene(root);
		
		Image background = new Image("file:background.png");
		ImageView imageView = new ImageView(background);
		root.getChildren().add(imageView);
		
		stage.setScene(currentScene);
		stage.setTitle("Roadfighter");
		stage.show();
		
	}
	
	public static void main(String[] args) {
		launch();
	}

}
