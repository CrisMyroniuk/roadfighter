package enemyTest;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import roadfighter.objects.BadDriver;
import roadfighter.objects.CarPlayer;
import roadfighter.objects.Direction;

class BadDriverTest {
	/*
	 * obstructPath() y moveTowardsObjective() se llamarian en update()
	 * antes de llamar a obstructPath() hay que preguntar if (!hasObstructed())
	 * en las pruebas es como si pasaran varios segundos entre un frame y el siguiente
	 */
	@Test
	void obstruyeAlPlayer() {
		double unSegundo = 1;
		BadDriver badDriver = new BadDriver(20, 20, Direction.UP);
		CarPlayer car = new CarPlayer(0, 0);
		
		badDriver.setSpeed(20);
		badDriver.obstructPath(car);
		badDriver.moveTowardsObjective(unSegundo);
		
		assertEquals(0, badDriver.getCoordinate().getX(), 0.01);
		assertEquals(20, badDriver.getCoordinate().getY(), 0.01);
	}
	
	@Test
	void seMueveUnaSolaVez() {
		double tresSegundos = 3;
		BadDriver badDriver = new BadDriver(40, 20, Direction.UP);
		CarPlayer car = new CarPlayer(0, 0);
		
		badDriver.setSpeed(10);
		badDriver.obstructPath(car);
		badDriver.moveTowardsObjective(tresSegundos);
		
		assertTrue(badDriver.hasObstructed());
	}

}
