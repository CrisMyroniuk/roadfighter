package roadfighter;

import java.io.IOException;

import com.google.gson.Gson;

import roadfighter.server.Server;
import roadfighter.server.utils.Message;
import roadfighter.server.utils.MessageType;

public class RoadFighterGame {
	
	public static void main(String[] args) {
//		Gson gson = new Gson();
//		System.out.println(gson.toJson(new Message(MessageType.LOBBY_NEW, "2")));
		
		try {
			Server server = new Server(20000);
			server.start();
		} catch (IOException e) {
			System.err.println("No se pudo iniciar el servidor");
			e.printStackTrace();
		}
	}
}
