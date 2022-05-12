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
	
	public Enemy getEnemy() {
		return enemy;
	}

	public void setEnemy(Enemy enemy) {
		this.enemy = enemy;
	}
	
	public Match(Player pla,Enemy ene) {
		setPlayer(pla);
		setEnemy(ene);
	}

	public static void main(String[] args) {

		Player jugador1=new Player(new CarPlayer(10, 0));
	
		Enemy enemigo1=new Enemy(10,10,Direction.UP);
		
		Match partida1=new Match(jugador1, enemigo1);
		
		if (Collide.crash(partida1.player.myCoord(), enemigo1.getCoordinate())) {
			System.out.println("chocaron bldo");
		}
		else
			System.out.println("no chocaron, todavia");
		
		partida1.player.setDireccion(Direction.UP);
		
		if (Collide.crash(partida1.player.myCoord(), enemigo1.getCoordinate())) {
			System.out.println("chocaron bldo");
		}
		else
			System.out.println("no chocaron, todavia");
			
		
		 
		
	}

	
}
