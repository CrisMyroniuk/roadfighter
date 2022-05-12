package itemTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import roadfighter.CarPlayer;
import roadfighter.PowerDown;

class PowerDownTest {

	CarPlayer car;
	
	@BeforeEach
	void init() {
		car = new CarPlayer(0.0 ,0.0);
	}
	
	@Test
	void efectoPowerDown() {
		int speedDecrease = 10;
		int initialSpeed = 10;
		PowerDown powerDown = new PowerDown(1, 1, 1, 1, speedDecrease);
		
		car.setSpeed(initialSpeed);
		powerDown.effectPlayer(car);
		
		assertEquals(0.0, car.getSpeed(), 0.01);
	}
	
	void desaparecer() {
		PowerDown powerDown = new PowerDown(1, 1, 1, 1, 1);
		powerDown.desaparecer();
		
		assertFalse(powerDown.isVisible());
		//si hay forma de comprobar que la animacion o lo que sea se reprodujo habria que agregarla aca
	}

}
