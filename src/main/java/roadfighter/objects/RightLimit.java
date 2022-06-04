package roadfighter.objects;

import roadfighter.Config;

public class RightLimit extends DeathLimit {
	
	public RightLimit() {
		super(1090, -10, 10, Config.baseHeight + 20);
	}
}
