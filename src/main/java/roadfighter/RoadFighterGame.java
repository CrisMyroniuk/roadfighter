package roadfighter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

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
	
	private LoginMenuSceneHandler loginMenuSceneHandler;
	private OnlineMenuSceneHandler onlineMenuSceneHandler;
	private LobbyCreatorSceneHandler lobbyCreatorSceneHandler;
	private LobbySelectorSceneHandler lobbySelectorSceneHandler;
	private LobbySceneHandler lobbySceneHandler;

	private Socket socket;
	private DataInputStream input;
	private DataOutputStream output;
	
	private String userName;
	
	@Override
	public void start(Stage stage) throws Exception {
		
		this.stage = stage;
		menuSceneHandler = new MenuSceneHandler(this);
		currentSceneHandler = menuSceneHandler;
		Scene scene = menuSceneHandler.getScene();
		stage.setScene(scene);
		
		menuSceneHandler.load();

		stage.getIcons().add(new Image("file:src/resources/images/flag-race.jpg"));
		stage.setTitle("Road Fighter");
		stage.setResizable(false);
		stage.show();
		
	}
	
	public static void main(String[] args) {
		launch();
	}
	
	public void startGame(boolean singlePlayer) {
		currentSceneHandler.unload();
		gameSceneHandler = new GameSceneHandler(this,singlePlayer);
		currentSceneHandler = gameSceneHandler;
		Scene scene = gameSceneHandler.getScene();
		stage.setScene(scene);
		gameSceneHandler.load();
	}
	
	public void startMenu() {
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
	
	public void startLoginMenu() {
		currentSceneHandler.unload();
		loginMenuSceneHandler = new LoginMenuSceneHandler(this);
		currentSceneHandler = loginMenuSceneHandler;
		Scene scene = loginMenuSceneHandler.getScene();
		stage.setScene(scene);
		loginMenuSceneHandler.load();
	}
	
	public void startOnlineMenu(DataInputStream input, DataOutputStream output) {
		this.input = input;
		this.output = output;
		
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
	
	public DataInputStream getInput() {
		return input;
	}
	
	public DataOutputStream getOutput() {
		return output;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String name) {
		userName = name;
	}
}
