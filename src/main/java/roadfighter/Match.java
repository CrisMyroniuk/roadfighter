package roadfighter;

public class Match {

	// public Map
	private Player player;
	
	private Enemy enemy;

	private Item item;
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public Match(Player pla) {
		setPlayer(pla);
	}

	public static void main(String[] args) {

		Player jugador1=new Player(new CarPlayer(10, 10));
	
		
		//Enemy enemigo1=new Enemy(10,10);
		
		 
		
	}

	
}
