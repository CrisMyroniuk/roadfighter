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
				System.out.println("esperando clientes(loginlistener linea 22)");
				Socket socket = listener.accept();
				System.out.println("entro alguien(loginlistener linea 24)");
				
				ClientListener nuevo = new ClientListener(socket, server);
				nuevo.start();
				
			} catch (IOException e) {
				System.err.println("no se pudo crear un hilo para un cliente nuevo.");
				e.printStackTrace();
			} 
		}
	}
}
