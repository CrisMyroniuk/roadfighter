package roadfighter.server.threads;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import com.google.gson.Gson;
import roadfighter.server.Server;
import roadfighter.server.utils.Message;
import roadfighter.server.utils.MessageType;

public class ClientListener extends Thread {
	
	DataInputStream input;
	Socket socket;
	Server server;
	
	public ClientListener(Socket socket, Server server) throws IOException {
		input = new DataInputStream(socket.getInputStream());
		this.socket = socket;
		this.server = server;
	}
	
	private boolean validate(Message request) {
		if (request.getType() == MessageType.SESSION_LOGIN)
			return !server.isConnected(request.getContent());
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
				server.add(new Client(request.getContent(), this));
				success = true;
			}
		} catch (SocketTimeoutException e) {
			try {
				socket.close();
				return false;
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
	
	@Override
	public void run() {
		if (!initConnection())
			return;
		
		String buffer;
		Message message;
		Gson gson = new Gson();
		
		while (true) {
			try {
				buffer = input.readUTF();
				message = gson.fromJson(buffer, Message.class);
				
				switch(message.getType()) {
				default:
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
