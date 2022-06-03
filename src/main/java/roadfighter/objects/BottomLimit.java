package roadfighter.objects;

import roadfighter.Config;

public class BottomLimit extends DeathLimit {

	public BottomLimit() {
		super(-10, Config.baseHeight + 50, Config.baseWidth + 20, 10);
	}
}
