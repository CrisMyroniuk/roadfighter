package roadfighter.server.threads;

import java.io.DataOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import com.google.gson.Gson;

import roadfighter.server.Lobby;
import roadfighter.server.utils.Message;
import roadfighter.server.utils.MessageType;

public class Client {
	
	private String userName;
	private ClientListener listener;
	private DataOutputStream salida;
	private Lobby lobby;
	private Gson gson;
	
	public Client(String name, ClientListener thread) throws IOException {
		userName = name;
		listener = thread;
		salida = listener.getSalida();
		gson = new Gson();
		
		salida.writeUTF(gson.toJson(new Message(MessageType.SESSION_LOGIN, "logedIn")));
	}
	
	public Client(String name) {
		userName = name;
		listener = null;
	}
	
	public void notify(Message message) {
		try {
			salida.writeUTF(gson.toJson(message, Message.class));
		} catch (IOException e) {
			System.err.println("fallo al responder al cliente " + userName);
		}
	}
	
	public void joinLobby(Lobby lobby) {
		this.lobby = lobby;
		notify(new Message(MessageType.LOBBY_JOIN, "joined"));
		lobby.add(this);
	}
	
	public Lobby createLobby(String name, int max) {
		lobby = new Lobby(name, this, max);
		notify(new Message(MessageType.LOBBY_NEW, "created"));
		return lobby;
	}
	
	public void leaveCurrentLobby() {
		lobby.remove(this);
		lobby = null;
		notify(new Message(MessageType.LOBBY_QUIT, "quited"));
	}
	
	public void setReady(String readyMessage) {
		if (readyMessage.equals("ready")) {
			lobby.readyClient(this, true);
			notify(new Message(MessageType.LOBBY_CONTROL, "ready"));
		}
		else if (readyMessage.equals("notReady")) {
			lobby.readyClient(this, false);
			notify(new Message(MessageType.LOBBY_CONTROL, "notReady"));
		}
		
	}
	
	public void quitLobby() {
		if (lobby != null)
			lobby.remove(this);
		lobby = null;
	}
	
	public void sendChatMessage(String message) {
		StringBuilder builder = new StringBuilder();
		builder.append("{" + userName + "}");
		Date hora = new Date();
		DateFormat formato = new SimpleDateFormat("HH:mm");
		builder.append("[" + formato.format(hora) + "]: ");
		builder.append(message);
		lobby.queueChatMessage(new Message(MessageType.LOBBY_CHAT, builder.toString()));
		notify(new Message(MessageType.LOBBY_CHAT, "sent"));
	}
	
	public synchronized void send(String message) throws IOException {
		salida.writeUTF(message);
	}
	
	public String getName() {
		return userName;
	}
	
	public ClientListener getListener() {
		return listener;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(userName, other.userName);
	}
	
	public Message pollActions() {
		return listener.pollActions();
	}
}
