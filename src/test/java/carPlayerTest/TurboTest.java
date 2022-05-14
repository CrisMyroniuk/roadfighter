package carPlayerTest;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import roadfighter.CarPlayer;

class TurboTest {
	
	
	CarPlayer cp;
	double prevSpeed;
	double prevAceleration;
	double time;
	double prevMaxSpeed;
	double TurboSpeed;
	double TurboMaxSpeed;
	double TurboAceleration;
	
	@BeforeEach
	public void setUp() {
		cp = new CarPlayer(0, 0);
		//prevSpeed = cp.getSpeed();
		prevAceleration = cp.getAceleration();
		prevMaxSpeed = cp.getSpeedLimit();

		cp.activateTurbo();
		
		//TurboSpeed = cp.getSpeed();
		TurboAceleration = cp.getAceleration();
		TurboMaxSpeed = cp.getSpeedLimit();
		
		time = 100;
	}
	
	@Test
	public void useTurbo() {
		
		assertTrue( cp.getTurbo().isTurbo());
	}
	
//	@Test
//	public void speedWithTurbo() {
//		
//		assertEquals(prevSpeed + 50, cp.getSpeed(), 0.01);
//	}
	
	@Test
	public void acelerationWithTurbo() {
		
		assertEquals(prevAceleration * 2, cp.getAceleration(), 0.01);
	}
	
	@Test
	public void maxSpeedWithTurbo() {
		
		assertEquals(prevMaxSpeed + 100, cp.getSpeedLimit(), 0.01);
	}
	
	
	
	@Test
	public void turnOffTurbo() {
		
		cp.updateTurboTime(time);
		assertFalse(cp.getTurbo().isTurbo());
	}
	
//	@Test
//	public void speedTurningOffTurbo() {
//		
//		cp.updateTurboTime(time);
//		assertEquals(TurboSpeed -50, cp.getSpeed(), 0.01);
//	}
	
	@Test
	public void acelerationTurningOffTurbo() {
		
		cp.updateTurboTime(time);
		assertEquals(TurboAceleration / 2, cp.getAceleration(), 0.01);
	}
	
	@Test
	public void maxSpeedTurningOffTurbo() {
		
		cp.updateTurboTime(time);
		assertEquals(TurboMaxSpeed -100, cp.getSpeedLimit(), 0.01);
	}
	
	
	

}
