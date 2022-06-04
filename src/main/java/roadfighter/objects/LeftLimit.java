package roadfighter.objects;

import roadfighter.Config;

public class LeftLimit extends DeathLimit {
	
	public LeftLimit() {
		super(410, -10, 10, Config.baseHeight + 20);
	}
}
