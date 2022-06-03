package roadfighter.utils;

import javafx.scene.media.AudioClip;

public class AudioResources {
	private static AudioClip create(String name) {
    	return new AudioClip(ClassLoader.getSystemResource(name).toString());
    }

	public static AudioClip getExplosionAudio() {
		return new AudioClip("file:src/resources/sound/explosion.mp3");
	}
	
	public static AudioClip getCoinAudio() {
		return new AudioClip("file:src/resources/sound/takeCoin.wav");
	}
}
