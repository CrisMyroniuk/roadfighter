package roadfighter;

public class Config {
	public final static int baseHeight = 1000;
	public final static int baseWidth = 1500;
	public final static int groundHeight = 80;
	
	public static double roadSpeed = 550;
	public static double enemySpeed = 200;
	public static double carHorizontalSpeed = 200;
	public static double carVerticalSpeed = 300;

	public final static int playerCenter = baseWidth / 3;
	
	public static int maxScore = 3000;
	
	public static double masterVolumeModifier = 0.5;
	public static double effectsVolumeModifier = 0.5;
	public static double musicVolumeModifier = 0.5;
	
	public static boolean music = true;
	public static boolean effects = true;

	private Config() {
	}
}