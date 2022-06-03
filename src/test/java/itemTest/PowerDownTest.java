package itemTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import roadfighter.objects.CarPlayer;
import roadfighter.objects.Player;
import roadfighter.objects.PowerDown;

class PowerDownTest {

	CarPlayer car;
	
	@BeforeEach
	void init() {
		Player player = new Player(new CarPlayer(0, 0));
	}
	
	@Test
	void efectoPowerDown() {
		int speedDecrease = 10;
		int initialSpeed = 10;
		PowerDown powerDown = new PowerDown(1, 1, null);
		
		car.setSpeed(initialSpeed);
		powerDown.effectPlayer(car);
		
		assertEquals(0.0, car.getSpeed(), 0.01);
	}
	
	void desaparecer() {
		PowerDown powerDown = new PowerDown(1, 1, null);
		powerDown.desaparecer();
		
		assertFalse(powerDown.isVisible());
		//si hay forma de comprobar que la animacion o lo que sea se reprodujo habria que agregarla aca
	}

}
