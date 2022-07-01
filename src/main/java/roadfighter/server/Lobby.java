package roadfighter.server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingQueue;

import com.google.gson.Gson;

import roadfighter.server.threads.Client;
import roadfighter.server.utils.Message;

public class Lobby extends Thread{
	
	public static final int MIN_CAPACITY = 2;
	public static final int MAX_CAPACITY = 4;
	
	private static Integer nextId = 0;
	private final Integer id;
	private HashMap<Client, Boolean> clients;
	private LinkedBlockingQueue<Message> chatMessages;
	
	private int capacity;
	
	public Lobby(Client creator, int capacity) {
		id = nextId;
		nextId++;
		
		this.capacity = capacity > MAX_CAPACITY ? MAX_CAPACITY : capacity;
		
		clients = new HashMap<Client, Boolean>();
		clients.put(creator, false);
		
		chatMessages = new LinkedBlockingQueue<Message>();
		System.out.println("lobby " + id + " creado (lobby 36)");
	}
	
	@Override
	public void run() {
		Gson gson = new Gson();
		Message chatMessage;
		
		while (true) {
			try {
				chatMessage = chatMessages.take();
				String chat = gson.toJson(chatMessage, Message.class);
				for (Client client : clients.keySet()) {
					try {
						client.send(chat);
					} catch (IOException e) {
						System.err.println("no se pudo mandar mensaje de chat a " + client.getName());
						e.printStackTrace();
					}
				} 
			} catch (InterruptedException e) {
				System.err.println("hilo de lobby " + id + " interrumpido.");
				e.printStackTrace();
			}
		}
	}
	
	public void readyClient(Client user, boolean ready) {
		if (clients.containsKey(user)) {
			clients.put(user, ready);
		}
	}
	
	public boolean queueChatMessage(Message chatMessage) {
		System.out.println("acolado: " + chatMessage.getContent() + "lobby 70");
		return chatMessages.offer(chatMessage);
	}
	
	public boolean add(Client user) {
		if (clients.size() < capacity) {
			clients.put(user, false);
			return true;
		}
		else
			return false;
	}
	
	public void remove(Client user) {
		clients.remove(user);
	}
	
	public int getPlayerCount() {
		return clients.size();
	}
	
	public Integer getInternalId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lobby other = (Lobby) obj;
		return Objects.equals(id, other.id);
	}
}