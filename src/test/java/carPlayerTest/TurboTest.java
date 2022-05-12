package carPlayerTest;



import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import roadfighter.CarPlayer;

public class TurboTest {
	
	
	CarPlayer cp = new CarPlayer(0, 0);
	
//	@Before
//	public void setUp() {
//		cp = new CarPlayer(0, 0, 1, 1, 80);
//		double prevSpeed = cp.getSpeed();
//		double prevMaxSpeed = cp.getMaximusSpeed();
//		double prevAceleration = cp.getAceleration();
//
//		double time = 100;
//
//		cp.activateTurbo();
//	}
	
	@Test
	public void useTurbo() {
		
		cp.activateTurbo();
		assertEquals(true, cp.isTurbo());
	}
	
	@Test
	public void speedWithTurbo() {
		
		double prevSpeed = cp.getSpeed();
		cp.activateTurbo();
		assertEquals(prevSpeed + 50, cp.getSpeed(), 0.01);
	}
	
	@Test
	public void maxSpeedWithTurbo() {
		
		double prevMaxSpeed = cp.getSpeedLimit();
		cp.activateTurbo();
		assertEquals(prevMaxSpeed + 50, cp.getSpeedLimit(), 0.01);
	}
	
	@Test
	public void acelerationWithTurbo() {
		
		double prevAceleration = cp.getAceleration();
		cp.activateTurbo();
		assertEquals(prevAceleration * 2, cp.getAceleration(), 0.01);
	}
	
	@Test
	public void turnOffTurbo() {
		
		cp.activateTurbo();
		
		double time = 100;
		cp.updateTurboTime(time);
		assertEquals(false, cp.isTurbo());
	}
	
	@Test
	public void speedTurningOffTurbo() {
		
		cp.activateTurbo();

		double time = 100;
		double TurboSpeed = cp.getSpeed();
		cp.updateTurboTime(time);
		assertEquals(TurboSpeed -50, cp.getSpeed(), 0.01);
	}
	
	@Test
	public void maxSpeedTurningOffTurbo() {
		
		cp.activateTurbo();

		double time = 100;
		double TurboMaxSpeed = cp.getSpeedLimit();
		cp.updateTurboTime(time);
		assertEquals(TurboMaxSpeed -50, cp.getSpeedLimit(), 0.01);
	}
	
	@Test
	public void acelerationTurningOffTurbo() {
		
		cp.activateTurbo();

		double time = 100;
		double TurboAceleration = cp.getAceleration();
		cp.updateTurboTime(time);
		assertEquals(TurboAceleration / 2, cp.getAceleration(), 0.01);
	}
	

}
