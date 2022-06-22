package roadfighter.server.threads;

public class Client {
	
	private String userName;
	
	public Client(String name, ClientListener thread) {
		userName = name;
	}
	
	public String getName() {
		return userName;
	}
}
