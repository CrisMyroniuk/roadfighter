package roadfighter.threads;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.LinkedBlockingQueue;

import com.google.gson.Gson;

import roadfighter.client.utils.Message;

public class ListenerThread extends Thread {
	
	private DataInputStream input;
	private Gson gson;
	
	private LinkedBlockingQueue<String> lobby;
	private LinkedBlockingQueue<String> online;
	private LinkedBlockingQueue<String> chat;
//	private LinkedBlickingQueue<String> game;
	
	public ListenerThread(Socket socket) {
		gson = new Gson();
		try {
			input = new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String pollLobbyMessage() {
		return lobby.poll();
	}
	
	public String takeLobbyMessage() throws InterruptedException {
		return lobby.take();
	}

	public String pollChatMessage() {
		return chat.poll();
	}
	
	public String takeChatMessage() throws InterruptedException {
		return chat.take();
	}

	public String pollOnlineMessage() {
		return online.poll();
	}
	
	public String takeOnlineMessage() throws InterruptedException {
		return online.take();
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
					lobby.offer(message.getContent());
					break;
				case LOBBY_CHAT:
					chat.offer(message.getContent());
					break;
				case LOBBY_NEW:
				case LOBBY_JOIN:
					online.offer(message.getContent());
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
