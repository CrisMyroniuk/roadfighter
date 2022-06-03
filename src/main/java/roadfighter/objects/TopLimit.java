package roadfighter.objects;

import roadfighter.Config;

public class TopLimit extends DeathLimit {
	
	public TopLimit() {
		super(-10, -10, Config.baseWidth + 20, 10);
	}
	
	public void effectPlayer(CarPlayer car) {
		car.loseControl(new Coordinate(0, 1));
	}
}
