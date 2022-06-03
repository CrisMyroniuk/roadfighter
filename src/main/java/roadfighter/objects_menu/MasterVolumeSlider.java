package roadfighter.objects_menu;

import roadfighter.Config;

public class MasterVolumeSlider extends SoundSlider {

	public MasterVolumeSlider(double x, double y) {
		super("General", x, y);
		
	}

	@Override
	protected void changeAsociatedValue(double newValue) {
		Config.masterVolumeModifier = newValue;
	}

}
