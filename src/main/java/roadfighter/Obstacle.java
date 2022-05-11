package roadfighter;

public class Obstacle extends Item{

	public Obstacle() {
		visible=true;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void effectPlayer(CarPlayer cp) {
		// TODO Auto-generated method stub
		cp.changeSpeed(0,Action.STOP);
	}

}
