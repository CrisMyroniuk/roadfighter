package roadfighter.match;


import roadfighter.objects.Enemy;
import roadfighter.objects.Item;
import roadfighter.objects.MatchMap;
import roadfighter.objects.Player;
import roadfighter.objects.PlayerState;

import java.util.ArrayList;

public class Match {
	
	private ArrayList<Item> items;
	private MatchMap map;
	private Player player;
	private Enemy enemy;

	public Player getPlayer() {
		return player;
	}

	public Enemy getEnemy() {
		return enemy;
	}

	public Match(Player pla, Enemy ene, MatchMap map, ArrayList<Item> items) {
		this.items = items;
		player = pla;
		enemy = ene;
		this.map = map;
	}

	public void verifyState() {
		
		double playerX = player.getCarPlayer().getCoordinate().getX();
		double playerY = player.getCarPlayer().getCoordinate().getY();
		double mapX = map.getMapDimension().getX();
		double mapY = map.getMapDimension().getY();
		
		if(playerY >= mapY && (playerX > 0 && playerX < mapX)) {
			player.changeStateWin();
		}
		
		if(playerX <= 0 || playerX >= mapX) {
			player.changeStateDeath();
		}
	}
	
	public boolean matchState() {
		if(player.getState() == PlayerState.PLAYER_DEATH || player.getState() == PlayerState.PLAYER_WINS)
			return false;
		else {
			return true;
		}
	}

/*	public static void main(String[] args) {

		ArrayList<Enemy> enemigos = new ArrayList<Enemy>();
		Random r = new Random();
		Scanner leer = new Scanner(System.in);

		for (int i = 0; i < 5; i++) {
			enemigos.add(new GoodDriver(r.nextInt(10 + 1) * 10, r.nextInt(10 + 1) * 10, Direction.UP));
			System.out.println("enemigo: "+enemigos.get(i).getCoordinate().toString());

		}
		Player jugador1 = new Player(new CarPlayer(10, 0));
		System.out.println("jugador: "+jugador1.myCoord().toString());
		while (true) {
			System.out.println("8 - ARRIBA");
			System.out.println("4 - IZQUIERDA");
			System.out.println("6 - DERECHA");
			System.out.println("2 - ABAJO");
			int num = leer.nextInt();
			switch (num) {
			case 8: {
				jugador1.setDireccion(Direction.UP);
				break;
			}
			case 4: {
				jugador1.setDireccion(Direction.DOWN);
				break;
			}
			case 6: {
				jugador1.setDireccion(Direction.LEFT);
				break;
			}
			case 2: {
				jugador1.setDireccion(Direction.RIGHT);
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + num);
			}
			for (Enemy enemy : enemigos) {
				if (Collide.crash(jugador1.myCoord(), enemy.getCoordinate())) {
					System.out.println("chocaron");
					return;
				} else {
					System.out.print("\n\n\n\n\n\n\n\n\n");
					for (int i = 0; i < 5; i++) {
						
						System.out.println("enemigo: "+enemigos.get(i).getCoordinate().toString());

					}
					System.out.println("jugador: "+jugador1.myCoord().toString());
				}
			}
		}
//
//		Enemy enemigo1 = new GoodDriver(10, 10, Direction.UP);
//
//		Match partida1 = new Match(jugador1, enemigo1);
//
//		if (Collide.crash(partida1.player.myCoord(), enemigo1.getCoordinate())) {
//			System.out.println("chocaron bldo");
//		} else
//			System.out.println("no chocaron, todavia");
//
//		partida1.player.setDireccion(Direction.UP);
//
//		if (Collide.crash(partida1.player.myCoord(), enemigo1.getCoordinate())) {
//			System.out.println("chocaron bldo");
//		} else
//			System.out.println("no chocaron, todavia");

	}*/

}
