package itemTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import roadfighter.CarPlayer;
import roadfighter.PowerUp;

class PowerUpTest {
	
	CarPlayer car;
	
	@BeforeEach
	void init() {
		car = new CarPlayer(0.0 ,0.0);
	}
	
	@Test
	void efectoPowerUp() {
		int puntos = 10;
		PowerUp powerUp = new PowerUp(1, 1, 1, 1, puntos);
		
		powerUp.effectPlayer(car);
		assertEquals(puntos, car.getPoint());
	}
	
	void desaparecer() {
		PowerUp powerUp = new PowerUp(1, 1, 1, 1, 1);
		powerUp.desaparecer();
		
		assertFalse(powerUp.isVisible());
		//si hay forma de comprobar que la animacion o lo que sea se reprodujo habria que agregarla aca
	}
}
