package matchTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import roadfighter.objects.CarPlayer;
import roadfighter.objects.Coordinate;
import roadfighter.objects.Enemy;
import roadfighter.objects.Item;
import roadfighter.objects.Match;
import roadfighter.objects.MatchMap;
import roadfighter.objects.Player;

class MatchTest {
	
	Match match;
	MatchMap map;
	Player player;
	Enemy enemy;
	ArrayList<Item> items;
	
	
	@BeforeEach
	void init() {
		items = null;
		player = new Player(new CarPlayer(40, 90));
		map = new MatchMap(new Coordinate(50,100));
		match = new Match(player, enemy, map, items);
	}
	
	@Test
	void testPlayerWin() {
		
		player.getCarPlayer().setDirectionUp();
		match.verifyState();
		assertFalse(match.matchState());
		
	}
	
	@Test
	void testPlayerDeath() {
		
		player.getCarPlayer().setDirectionRight();
		match.verifyState();
		assertFalse(match.matchState());
		
	}

}
