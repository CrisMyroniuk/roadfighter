package carPlayerTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import roadfighter.objects.CarPlayer;
import roadfighter.objects.Coordinate;

class MovementTest {

	CarPlayer car;
	double x;
	double y;
	
	@BeforeEach
	void init() {
		car = new CarPlayer(0, 0);
		x = 10;
		y = 10;
	}
	
	@Test
	void moveUp() {
		
		Coordinate newPosition = new Coordinate(car.getCoordinate().getX(), car.getCoordinate().getY() + y);
		car.setDirectionUp();
		assertTrue(car.getCoordinate().equals(newPosition));
	}
	
	@Test
	void moveDown() {
		
		Coordinate newPosition = new Coordinate(car.getCoordinate().getX(), car.getCoordinate().getY() - y);
		car.setDirectionDown();
		//assertEquals(newPosition, car.getCoordinate());
		assertTrue(car.getCoordinate().equals(newPosition));
	}
	
	@Test
	void moveRight() {
		
		Coordinate newPosition = new Coordinate(car.getCoordinate().getX() + x, car.getCoordinate().getY());
		car.setDirectionRight();
//		assertEquals(newPosition, car.getCoordinate());
		assertTrue(car.getCoordinate().equals(newPosition));
	}
	
	@Test
	void moveLeft() {
		
		Coordinate newPosition = new Coordinate(car.getCoordinate().getX() - x, car.getCoordinate().getY());
		car.setDirectionLeft();
//		assertEquals(newPosition, car.getCoordinate());
		assertTrue(car.getCoordinate().equals(newPosition));
	}

}
