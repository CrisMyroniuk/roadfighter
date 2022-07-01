package roadfighter.server;

import java.io.IOException;
import java.util.HashMap;

import roadfighter.server.threads.Client;
import roadfighter.server.threads.LoginListener;
import roadfighter.server.utils.Message;
import roadfighter.server.utils.MessageType;

public class Server {
	
	private HashMap<String, Client> clients;
	private HashMap<Integer, Lobby> lobbies;
	private LoginListener listener;
	
	public Server(int port) throws IOException {
		lobbies = new HashMap<Integer, Lobby>();
		clients = new HashMap<String, Client>();
		listener = new LoginListener(port, this);
	}
	
	public boolean isConnected(Client user) {
		return clients.containsKey(user.getName());
	}
	
	public synchronized void removeClient(Client client) {
		clients.remove(client.getName());
	}
	
	public synchronized void add(Client newClient) {
		clients.put(newClient.getName(), newClient);
		System.out.println("se añadio a " + newClient.getName() + " (server 27)");
	}
	
	public synchronized void joinLobby(Client user, int lobbyId) {
		if (clients.containsKey(user.getName())) {
			clients.get(user.getName()).joinLobby(lobbies.get(lobbyId));
			System.out.println(user.getName() + " se unio al lobby " + lobbyId + "(server 33)");
		}
	}
	
	public synchronized Lobby createNewLobby(Client creator, int max) {
		Lobby nuevo = creator.createLobby(max);
		lobbies.put(nuevo.getInternalId(), nuevo);
		nuevo.start();
		return nuevo;
	}
	
	public void start() {
		listener.start();
	}
}
