package itemTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import roadfighter.objects.CarPlayer;
import roadfighter.objects.Player;
import roadfighter.objects.PowerUp;

class PowerUpTest {
	
	Player player;
	CarPlayer car;
	
	@BeforeEach
	void init() {
		
		Player player = new Player(new CarPlayer(0, 0));
	}
	
	@Test
	void efectoPowerUp() {
		int puntos = 10;
		PowerUp powerUp = new PowerUp(1, 1, puntos, null);
		
		powerUp.effectPlayer(car);
		assertEquals(puntos, player.getCarPlayer().getPoint());
	}
	
	void desaparecer() {
		PowerUp powerUp = new PowerUp(1, 1, 1, null);
		powerUp.desaparecer();
		
		assertFalse(powerUp.isVisible());
		//si hay forma de comprobar que la animacion o lo que sea se reprodujo habria que agregarla aca
	}
}
