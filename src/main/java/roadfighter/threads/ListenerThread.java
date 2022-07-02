package roadfighter.threads;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.concurrent.LinkedBlockingQueue;

import com.google.gson.Gson;

import roadfighter.client.utils.Message;

public class ListenerThread extends Thread {
	
	private DataInputStream input;
	private Gson gson;
	
	private LinkedBlockingQueue<Message> lobby;
	private LinkedBlockingQueue<Message> online;
	private LinkedBlockingQueue<Message> chat;
	private LinkedBlockingQueue<Message> game;
	
	public ListenerThread(DataInputStream input) {
		lobby = new LinkedBlockingQueue<Message>();
		online = new LinkedBlockingQueue<Message>();
		chat = new LinkedBlockingQueue<Message>();
		game = new LinkedBlockingQueue<Message>();
		
		gson = new Gson();
		
		this.input = input;
//		try {
//			input = new DataInputStream(socket.getInputStream());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	public Message pollLobbyMessage() {
		return lobby.poll();
	}
	
	public Message takeLobbyMessage() throws InterruptedException {
		return lobby.take();
	}

	public Message pollChatMessage() {
		return chat.poll();
	}
	
	public Message takeChatMessage() throws InterruptedException {
		return chat.take();
	}

	public Message pollOnlineMessage() {
		return online.poll();
	}
	
	public Message takeOnlineMessage() throws InterruptedException {
		return online.take();
	}
	
	public Message pollGameMessage() {
		return game.poll();
	}
	
	@Override
	public void run() {
		String buffer;
		Message message;
		while(true) {
			try {
				buffer = input.readUTF();
				message = gson.fromJson(buffer, Message.class);
				
				switch(message.getType()) {
				case LOBBY_QUIT:
				case LOBBY_CONTROL:
					lobby.offer(message);
					break;
				case LOBBY_CHAT:
					chat.offer(message);
					break;
				case LOBBY_NEW:
				case LOBBY_JOIN:
					online.offer(message);
					break;
				case PLAYER_MOVE:
				case PLAYER_STOP:
				case PLAYER_OTHER:
				case PLAYER_OTHER_MOVE:
				case PLAYER_OTHER_STOP:
				case ITEM_NEW:
				case OBSTACLE_NEW:
				case PDOWN_NEW:
				case ENEMY_NEW:
					game.offer(message);
					break;
				default:
					break;
				}
			} catch (SocketException e) {
				System.err.println("se perdio la conexion");
				break;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
