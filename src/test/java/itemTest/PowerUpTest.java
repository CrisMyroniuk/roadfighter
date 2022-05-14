package itemTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import roadfighter.CarPlayer;
import roadfighter.Player;
import roadfighter.PowerUp;

class PowerUpTest {
	
	Player player;
	CarPlayer car;
	
	@BeforeEach
	void init() {
		
		car = new CarPlayer(0.0 ,0.0);
		player = new Player(car);
	}
	
	@Test
	void efectoPowerUp() {
		int puntos = 10;
		PowerUp powerUp = new PowerUp(1, 1, 1, 1, puntos);
		
		powerUp.effectPlayer(car);
		assertEquals(puntos, player.getPoint());
	}
	
	void desaparecer() {
		PowerUp powerUp = new PowerUp(1, 1, 1, 1, 1);
		powerUp.desaparecer();
		
		assertFalse(powerUp.isVisible());
		//si hay forma de comprobar que la animacion o lo que sea se reprodujo habria que agregarla aca
	}
}
