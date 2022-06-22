package roadfighter.server.threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import roadfighter.server.Server;

public class LoginListener extends Thread {
	
	private ServerSocket listener;
	private Server server;
	
	public LoginListener(int port, Server server) throws IOException {
		listener = new ServerSocket(port);
		this.server = server;
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Socket socket = listener.accept();
				
				ClientListener nuevo = new ClientListener(socket, server);
				
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}
}
