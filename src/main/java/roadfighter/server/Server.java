package roadfighter.server;

import java.io.IOException;
import java.util.HashMap;

import roadfighter.server.threads.Client;
import roadfighter.server.threads.GameThread;
import roadfighter.server.threads.LoginListener;
import roadfighter.server.utils.Message;
import roadfighter.server.utils.MessageType;

public class Server {
	
	private HashMap<String, Client> clients;
	private HashMap<String, Lobby> lobbies;
	private LoginListener listener;
	
	public Server(int port) throws IOException {
		lobbies = new HashMap<String, Lobby>();
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
		System.out.println("se anyadio a " + newClient.getName() + " (server 27)");
	}
	
	public synchronized void joinLobby(Client user, String lobbyName) {
		if (clients.containsKey(user.getName()) && lobbies.containsKey(lobbyName)) {
			clients.get(user.getName()).joinLobby(lobbies.get(lobbyName));
			System.out.println(user.getName() + " se unio al lobby " + lobbyName + "(server 33)");
		} else {
			user.notify(new Message(MessageType.LOBBY_JOIN, "notFound"));
		}
		
		if (lobbies.get(lobbyName).getPlayerCount() == 2) {
			GameThread game = new GameThread(lobbies.get(lobbyName));
			game.start();
		}
	}
	
	public synchronized boolean createNewLobby(Client creator, String name, int max) {
		if (!lobbies.containsKey(name)) {
			Lobby nuevo = creator.createLobby(name, max);
			lobbies.put(nuevo.getLobbyName(), nuevo);
			nuevo.start();
			return true;
		} else {
			creator.notify(new Message(MessageType.LOBBY_NEW, "inUse"));
			return false;
		}
	}
	
	public void start() {
		listener.start();
	}
}
