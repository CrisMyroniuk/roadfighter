package roadfighter.objects_menu;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import roadfighter.interfaces.Renderable;
import roadfighter.utils.GameObject;

public class ChatMenu extends GameObject implements Renderable{
	
	private VBox render;
	private VBox connectedButtons;
	private ListView<String> connected;
	private HBox messagesConnectedButtons;
	private HBox chatSend;
	private TextField chat;
	private ListView<String> messages;
	private ScrollPane messagesBox;
	private Button send;
	private Button quit;
	private Button ready;
	
	public ChatMenu(double x, double y) {
		chat = new TextField();
		send = new Button("send");
		chatSend = new HBox(chat, send);
		
		connected = new ListView<String>();
		ready = new Button("ready");
		quit = new Button("quit");
		connectedButtons = new VBox(connected, ready, quit);
		
		messages = new ListView<String>();
		messagesBox = new ScrollPane(messages);
		messagesConnectedButtons = new HBox(messagesBox, connectedButtons);
		
		connected.setMaxHeight(200);
		connected.setMaxWidth(100);
		messagesBox.setMaxHeight(300);
		messagesBox.setMinWidth(500);
		chat.setMinWidth(550);
		messages.setMinWidth(300);
		ready.setMinWidth(100);
		ready.setPrefHeight(50);
		quit.setMinWidth(100);
		quit.setPrefHeight(50);
		
		render = new VBox(messagesConnectedButtons, chatSend);
		render.setTranslateX(x);
		render.setTranslateY(y);
		
		ready.setStyle("-fx-background-color: rgba(255,255,255,0)");
	}
	
	public void addChatMessage(String message) {
		messages.getItems().add(message);
	}
	
	public void addUser(String user) {
		connected.getItems().add(user);
	}
	
	public void removeUser(String user) {
		connected.getItems().remove(user);
	}
	
	public Button getReadyButton() {
		return ready;
	}
	
	public Button getQuitButton() {
		return quit;
	}
	
	public Button getSendButton() {
		return send;
	}
	
	@Override
	public Node getRender() {
		return render;
	}

	@Override
	public void destroy() {}

}
