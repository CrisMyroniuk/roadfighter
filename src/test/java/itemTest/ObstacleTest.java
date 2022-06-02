package itemTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import roadfighter.objects.CarPlayer;
import roadfighter.objects.Obstacle;
import roadfighter.objects.Player;

class ObstacleTest {
	
	/* varios archivos diferentes para poder
	 * agregar mas pruebas especificas
	 */
	
	CarPlayer car;
	
	@BeforeEach
	void init() {
		Player player = new Player(new CarPlayer(0, 0));
	}
	
	@Test
	void efectoObstaculo() {
		car.setSpeed(10);
		Obstacle obstacle = new Obstacle(0, 0);
		
		obstacle.effectPlayer(car);
		
		assertEquals(0.0, car.getSpeed(), 0.01);
	}

}
