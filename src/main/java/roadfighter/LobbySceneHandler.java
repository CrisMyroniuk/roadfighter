package roadfighter;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import roadfighter.client.utils.Message;
import roadfighter.client.utils.MessageType;
import roadfighter.objects.Background;
import roadfighter.objects_menu.ButtonMenu;
import roadfighter.objects_menu.ChatMenu;
import roadfighter.objects_menu.Title;
import roadfighter.threads.ListenerThread;
import roadfighter.utils.GameObject;
import roadfighter.utils.GameObjectBuilder;

public class LobbySceneHandler extends SceneHandler {
	private Group rootGroup;
	private Background background;
	private ArrayList<GameObject> gameObjects=new ArrayList<GameObject>();
	private Title title;
	private GameObjectBuilder gameOB;
	
	private ChatMenu chat;
	private ButtonMenu back;

	private EventHandler<ActionEvent> onPressHandlerReady;
	private EventHandler<ActionEvent> onPressHandlerBack;
	
	private boolean readyState;
	
	private ListenerThread servidor;
	
	public LobbySceneHandler(RoadFighterGame g) {
		super(g);
		readyState = false;
	}

	protected void prepareScene() {
		rootGroup = new Group();
		scene = new Scene(rootGroup, Config.baseWidth, Config.baseHeight, Color.BLACK);
	}

	protected void defineEventHandlers() {
		mouseEventHandler = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
//				if (event.getButton() == MouseButton.PRIMARY) {
//					g.startGame();
//				}
			}
		};

		keyEventHandler = new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				switch (e.getCode()) {
				case ESCAPE:
					g.startMenu();
					break;
				default:
					break;
				}
			}
		};
		
		onPressHandlerReady = new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) {
				sendReady(readyState);
			}
		};
		
		onPressHandlerBack = new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent e) {
				g.startMenu();
			}
		};
	}

	private void sendReady(boolean readyState) {
		DataOutputStream output = g.getOutput();
		try {
			output.writeUTF(new Message(MessageType.LOBBY_CONTROL, readyState ? "ready" : "notReady").getJsonString());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public void load() {
		Group baseGroup = new Group();
		rootGroup.getChildren().add(baseGroup);
		
		gameOB = GameObjectBuilder.getInstance();
		gameOB.setRootNode(baseGroup);
		
		background = new Background();

		title = new Title();
		
		chat = new ChatMenu(400, Config.baseHeight * 3 / 5 - 200);
		back = new ButtonMenu("BACK", Config.baseHeight * 3 / 5 + 300);
		
		chat.addUser(g.getUserName());
		chat.setReady(false);
		
		gameOB.add(chat, back);
		
		gameObjects.add(background);
		gameObjects.add(title);
		gameOB.add(gameObjects);
		
		servidor = new ListenerThread(g.getInput());
		servidor.start();

		addTimeEventsAnimationTimer();
		addInputEvents();
	}
	
	@Override
	public void update(double delta) {
		super.update(delta);
		Message message = servidor.pollLobbyMessage();
		if (message != null) {
			switch(message.getType()) {
			case LOBBY_CONTROL:
				if (message.getContent().equals("ready")) {
					chat.setReady(true);
					readyState = true;
				}
				else if (message.getContent().equals("notReady")) {
					chat.setReady(false);					
					readyState = false;
				}
				else 
					chat.addUser(message.getContent());
				break;
			default:
				break;
			}
		}
	}

	public void unload() {
		rootGroup.getChildren().remove(0);
		super.unload();
	}
	
	protected void addInputEvents() {
		super.addInputEvents();
		chat.getReadyButton().setOnAction(onPressHandlerReady);
//		chat.getQuitButton().setOnAction(onPressHandlerQuit);
//		chat.getSendButton().setOnAction(OnpressHandlerSend);
		back.setOnAction(onPressHandlerBack);
	}
	
	protected void removeInputEvents() {
		super.removeInputEvents();
		chat.getReadyButton().removeEventFilter(ActionEvent.ACTION, onPressHandlerReady);
//		quit.getReadyButton().removeEventFilter(ActionEvent.ACTION, onPressHandlerQuit);
//		send.getReadyButton().removeEventFilter(ActionEvent.ACTION, onPressHandlerSend);
		back.removeOnAction(onPressHandlerBack);
	}
}
