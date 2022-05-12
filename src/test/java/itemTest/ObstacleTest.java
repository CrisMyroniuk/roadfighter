package itemTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import roadfighter.CarPlayer;
import roadfighter.Obstacle;

class ObstacleTest {
	
	/* varios archivos diferentes para poder
	 * agregar mas pruebas especificas
	 */
	
	CarPlayer car;
	
	@BeforeEach
	void init() {
		car = new CarPlayer(0.0 ,0.0);
	}
	
	@Test
	void efectoObstaculo() {
		car.setSpeed(10);
		Obstacle obstacle = new Obstacle();
		
		obstacle.effectPlayer(car);
		
		assertEquals(0.0, car.getSpeed(), 0.01);
	}

}
