package roadfighter.server;

import java.io.IOException;
import java.util.HashMap;

import roadfighter.server.threads.Client;
import roadfighter.server.threads.LoginListener;

public class Server {
	
	private HashMap<String, Client> clients;
	private HashMap<Integer, Lobby> lobbies;
	private LoginListener listener;
	
	public Server(int port) throws IOException {
		lobbies = new HashMap<Integer, Lobby>();
		clients = new HashMap<String, Client>();
		listener = new LoginListener(port, this);
	}
	
	public boolean isConnected(String user) {
		return clients.containsKey(user);
	}
	
	public synchronized void add(Client clienteNuevo) {
		clients.put(clienteNuevo.getName(), clienteNuevo);
	}
	
	public void start() {
		listener.start();
	}
}
