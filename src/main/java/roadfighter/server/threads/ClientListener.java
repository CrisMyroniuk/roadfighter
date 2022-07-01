package roadfighter.server.threads;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import com.google.gson.Gson;

import roadfighter.server.Lobby;
import roadfighter.server.Server;
import roadfighter.server.utils.Message;
import roadfighter.server.utils.MessageType;

public class ClientListener extends Thread {
	
	private DataInputStream input;
	private Socket socket;
	private final Server server;
	private Client client;
	
	public ClientListener(Socket socket, Server server) throws IOException {
		this.socket = socket;
		this.server = server;
		input = new DataInputStream(socket.getInputStream());
	}
	
	private boolean validate(Message request) {
		if (request.getType() == MessageType.SESSION_LOGIN)
			return !server.isConnected(new Client(request.getContent()));
		return false;
	}
	
	private boolean initConnection() {
		boolean success = false;
		try {
			socket.setSoTimeout(60000); //timeout seteado a un minuto, capas es mucho
			String buffer = input.readUTF();
			Gson gson = new Gson();
			Message request = gson.fromJson(buffer, Message.class);
			
			if (validate(request)) {
				client = new Client(request.getContent(), this);
				server.add(client);
				socket.setSoTimeout(0);
				success = true;
			}
		} catch (SocketTimeoutException e) {
			try {
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.err.println("Un cliente inicio una conexion y se quedo callado, timedout");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return success;
	}
	
	public Server getServer() {
		return server;
	}
	
	public DataOutputStream getSalida() throws IOException {
		return new DataOutputStream(socket.getOutputStream());
	}
	
	@Override
	public void run() {
		if (!initConnection())
			return;
		System.out.println("se creo el cliente y eso (clientListener 73)");
		String buffer;
		Message message;
		Gson gson = new Gson();
		
		while (true) {
			try {
				buffer = input.readUTF();
				System.out.println(buffer + "(clientListener 81)");
				message = gson.fromJson(buffer, Message.class);
				
				switch(message.getType()) {
				case LOBBY_NEW:
					buffer = input.readUTF();
					Message message2 = gson.fromJson(buffer, Message.class);
					if (message2.getType().equals(MessageType.LOBBY_NEW)) {					
						server.createNewLobby(client, message2.getContent(), Integer.parseInt(message.getContent()));
					}
					break;
				case LOBBY_JOIN:
					server.joinLobby(client, Integer.parseInt(message.getContent()));
					break;
				case LOBBY_CONTROL:
					client.setReady(message.getContent());
					break;
				case LOBBY_QUIT:
					client.quitLobby();
					break;
				case LOBBY_CHAT:
					client.sendChatMessage(message.getContent());
					break;
				case SESSION_LOGOUT:
					client.quitLobby();
					server.removeClient(client);
				default:
					break;
				}
			} catch (IOException e) {
				client.leaveCurrentLobby();
				server.removeClient(client);
				System.err.println("el cliente cerro la conexion inesperadamente.");
				break;
			}
			
		}
	}
}
