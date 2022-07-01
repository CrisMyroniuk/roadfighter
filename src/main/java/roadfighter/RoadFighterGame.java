package roadfighter;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class RoadFighterGame extends Application {
	
	private Stage stage;
	
	private SceneHandler currentSceneHandler;

	private MenuSceneHandler menuSceneHandler;
	private OptionsSceneHandler optionsSceneHandler;

	private GameSceneHandler gameSceneHandler;
	private WinSceneHandler winSceneHandler;
	
	private OnlineMenuSceneHandler onlineMenuSceneHandler;
	private LobbyCreatorSceneHandler lobbyCreatorSceneHandler;
	private LobbySelectorSceneHandler lobbySelectorSceneHandler;
	private LobbySceneHandler lobbySceneHandler;

	
	@Override
	public void start(Stage stage) throws Exception {
		/*Group root = new Group();
		Scene currentScene = new Scene(root);
		
		Image background = new Image("file:background.png");
		ImageView imageView = new ImageView(background);
		root.getChildren().add(imageView);
		
		stage.setScene(currentScene);
		stage.setTitle("Roadfighter");
		stage.show();*/
		
		this.stage = stage;

		menuSceneHandler = new MenuSceneHandler(this);
		currentSceneHandler = menuSceneHandler;
		Scene scene = menuSceneHandler.getScene();
		stage.setScene(scene);
		
		menuSceneHandler.load();
		
		// XXX patron state para controlar paso de escenas?

		// Scale
		// TODO scale and fill to maintain proportion (also center)
		// scale = new Scale();
		// dinamico, cada vez que cambio el tamaÃ±o de ventana
		// scale.setX(scene.getWidth() / WIDTH);
		// scale.setY(scene.getHeight() / HEIGHT);
		// images.getTransforms().add(scale);

		stage.getIcons().add(new Image("file:src/resources/images/flag-race.jpg"));
		stage.setTitle("Road Fighter");
		stage.setResizable(false);
		stage.show();
		
	}
	
	public static void main(String[] args) {
		launch();
	}
	
	public void startGame(boolean singlePlayer) {
		//menuSceneHandler.unload();
		currentSceneHandler.unload();
		gameSceneHandler = new GameSceneHandler(this,singlePlayer);
		currentSceneHandler = gameSceneHandler;
		Scene scene = gameSceneHandler.getScene();
		stage.setScene(scene);
		gameSceneHandler.load();
	}
	
	public void startMenu() {
//		if (fromOptions)
//			optionsSceneHandler.unload();
//		else
//			gameSceneHandler.unload();
		currentSceneHandler.unload();
		menuSceneHandler = new MenuSceneHandler(this);
		currentSceneHandler = menuSceneHandler;
		Scene scene = menuSceneHandler.getScene();
		stage.setScene(scene);
		menuSceneHandler.load();
	}
	
	public void startOptions() {
		currentSceneHandler.unload();
		optionsSceneHandler = new OptionsSceneHandler(this);
		currentSceneHandler = optionsSceneHandler;
		Scene scene = optionsSceneHandler.getScene();
		stage.setScene(scene);
		optionsSceneHandler.load();
	}
	
	public void startWin(String winner, boolean singlePlayer) {
		currentSceneHandler.unload();
		winSceneHandler = new WinSceneHandler(this, winner, singlePlayer);
		currentSceneHandler = winSceneHandler;
		Scene scene = winSceneHandler.getScene();
		stage.setScene(scene);
		winSceneHandler.load();
	}
	
	public void startOnlineMenu() {
		currentSceneHandler.unload();
		onlineMenuSceneHandler = new OnlineMenuSceneHandler(this);
		currentSceneHandler = onlineMenuSceneHandler;
		Scene scene = onlineMenuSceneHandler.getScene();
		stage.setScene(scene);
		onlineMenuSceneHandler.load();
	}
	
	public void startLobbyCreatorMenu() {
		currentSceneHandler.unload();
		lobbyCreatorSceneHandler = new LobbyCreatorSceneHandler(this);
		currentSceneHandler = lobbyCreatorSceneHandler;
		Scene scene = lobbyCreatorSceneHandler.getScene();
		stage.setScene(scene);
		lobbyCreatorSceneHandler.load();
	}
	
	public void startLobbySelectorMenu() {
		currentSceneHandler.unload();
		lobbySelectorSceneHandler = new LobbySelectorSceneHandler(this);
		currentSceneHandler = lobbySelectorSceneHandler;
		Scene scene = lobbySelectorSceneHandler.getScene();
		stage.setScene(scene);
		lobbySelectorSceneHandler.load();
	}
	
	public void startLobbyScreen() {
		currentSceneHandler.unload();
		lobbySceneHandler = new LobbySceneHandler(this);
		currentSceneHandler = lobbySceneHandler;
		Scene scene = lobbySceneHandler.getScene();
		stage.setScene(scene);
		lobbySceneHandler.load();
	}

}
