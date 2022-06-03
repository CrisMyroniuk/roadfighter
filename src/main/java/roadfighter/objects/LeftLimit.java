package roadfighter.objects;

import roadfighter.Config;

public class LeftLimit extends DeathLimit {
	
	public LeftLimit() {
		super(400, -10, 10, Config.baseHeight + 20);
	}
}
