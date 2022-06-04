package roadfighter.objects_menu;

import roadfighter.Config;

public class EffectsVolumeSlider extends SoundSlider {

	public EffectsVolumeSlider(double x, double y) {
		super("Efectos", x, y, Config.effectsVolumeModifier);
		
	}

	@Override
	protected void changeAsociatedValue(double newValue) {
		Config.effectsVolumeModifier = newValue;
	}

}
