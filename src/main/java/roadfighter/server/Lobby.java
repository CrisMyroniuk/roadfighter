package roadfighter.server;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

import com.google.gson.Gson;

import roadfighter.server.threads.Client;
import roadfighter.server.utils.Message;
import roadfighter.server.utils.MessageType;

public class Lobby extends Thread{
	
	public static final int MIN_CAPACITY = 2;
	public static final int MAX_CAPACITY = 4;

	private HashMap<Client, Boolean> clients;
	private LinkedBlockingQueue<Message> chatMessages;
	private LinkedBlockingQueue<Message> lobbyMessages;
	
	private String name;
	private int capacity;
	
	public Lobby(String name, Client creator, int capacity) {
		
		this.capacity = capacity > MAX_CAPACITY ? MAX_CAPACITY : capacity;
		this.name = name;
		
		clients = new HashMap<Client, Boolean>();
		clients.put(creator, false);
		
		chatMessages = new LinkedBlockingQueue<Message>();
		lobbyMessages = new LinkedBlockingQueue<Message>();
		System.out.println("lobby " + name + " creado (lobby 34)");
	}
	
	@Override
	public void run() {
		Gson gson = new Gson();
		Message chatMessage;
		Message lobbyMessage;
		
		while (true) {
			try {
				lobbyMessage = lobbyMessages.take();
				String lobby = gson.toJson(lobbyMessage, Message.class);
				for (Client client : clients.keySet()) {
					try {
						client.send(lobby);
					} catch (IOException e) {
						System.err.println("no se pudo mandar mensaje de lobby a " + client.getName());
						e.printStackTrace();
					}
				} 
				
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
				System.err.println("hilo de lobby " + name + " interrumpido.");
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
	
	public boolean queueLobbyMessage(Message lobbyMessage) {
		System.out.println("acolado: " + lobbyMessage.getContent() + "lobby 73");
		return chatMessages.offer(lobbyMessage);
	}
	
	public boolean add(Client user) {
		if (clients.size() < capacity) {
			clients.put(user, false);
			lobbyMessages.offer(new Message(MessageType.LOBBY_JOIN, user.getName()));
			for (Client current : clients.keySet()) {
				user.notify(new Message(MessageType.LOBBY_JOIN, current.getName()));
			}
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
	
	public String getLobbyName() {
		return name;
	}
	
	public Set<Client> getClientsList() {
		return clients.keySet();
	}
}