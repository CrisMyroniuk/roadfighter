package roadfighter.server;

import java.util.HashMap;

import roadfighter.server.threads.Client;

public class Lobby {
	
	public static final int MIN_CAPACITY = 2;
	public static final int MAX_CAPACITY = 4;
	
	private static Integer nextId = 0;
	private final Integer id;
	private HashMap<String, Client> clients;
	
	private int capacity;
	
	public Lobby(Client creator, int capacity) {
		id = nextId;
		nextId++;
		
		this.capacity = capacity > MAX_CAPACITY ? MAX_CAPACITY : capacity;
		
		clients = new HashMap<String, Client>();
		clients.put(creator.getName(), creator);
	}
	
	public boolean add(Client user) {
		if (clients.size() < capacity) {
			clients.put(user.getName(), user);
			return true;
		}
		else
			return false;
	}
	
	public void remove(String user) {
		clients.remove(user);
	}
	
	public void remove(Client user) {
		clients.remove(user.getName());
	}
	
	public int getPlayerCount() {
		return clients.size();
	}
	
	public Integer getId() {
		return id;
	}
}