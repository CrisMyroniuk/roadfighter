package roadfighter.objects;

import roadfighter.Config;

public class RightLimit extends DeathLimit {
	
	public RightLimit() {
		super(Config.baseWidth + 200, -10, 10, Config.baseHeight + 20);
	}
}
