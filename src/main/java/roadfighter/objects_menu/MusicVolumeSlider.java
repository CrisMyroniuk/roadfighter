package roadfighter.objects_menu;

import roadfighter.Config;

public class MusicVolumeSlider extends SoundSlider {

	public MusicVolumeSlider(double x, double y) {
		super("Musica", x, y);
	}

	@Override
	protected void changeAsociatedValue(double newValue) {
		Config.musicVolumeModifier = newValue;
	}

}
