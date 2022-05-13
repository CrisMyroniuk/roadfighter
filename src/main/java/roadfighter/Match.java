package roadfighter;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;

public class Match {

	// public Map
	private Player player;
	private Enemy enemy;
	private Item item;
	private Enum state;

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

	public Match(Player pla, Enemy ene) {
		setPlayer(pla);
		setEnemy(ene);
	}

	public boolean matchState() {
		// Retorna el estado de la partida
		return true;
	}

	public static void main(String[] args) {

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

	}

}
