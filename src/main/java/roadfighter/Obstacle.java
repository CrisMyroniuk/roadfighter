package roadfighter;

public class Obstacle extends Item{

	public Obstacle() {
		visible=true;
	}

	@Override
	public void effectPlayer(CarPlayer cp) {
		cp.changeSpeed(0,Action.STOP);
	}

}
