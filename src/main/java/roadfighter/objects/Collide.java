package roadfighter.objects;

public class Collide {

	public static boolean crash(Coordinate c1,Coordinate c2){
		return c1.intersection(c2);
	}
}
