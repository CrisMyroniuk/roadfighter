package roadfighter.objects;

import javafx.scene.Node;
import javafx.scene.shape.Shape;

public class PowerDown extends Item{

	private double speedDown;
	
	public double getSpeedDown() {
		return speedDown;
	}

	public void setSpeedDown(double speedDown) {
		this.speedDown = speedDown;
	}
	
	public PowerDown(int x, int y,double h, double w, double sp) {
		setCoordinate(new Coordinate(x, y));
		setHeight(h);
		setWidth(w);
		setModel(null);
		setSpeedDown(sp);
		visible=true;
		
	}

	@Override
	public void effectPlayer(CarPlayer cp) {
		// Baja la velocidad del auto
		cp.changeSpeed(10,Action.SPEED_DOWN);
	}

	public void desaparecer() {
		//To do llamar a que haga alguna animacion y/o sonido
		visible = false;
	}
	
	@Override
	public void destroy() {
		//cortar audio
	}

	@Override
	public Shape getCollider() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node getRender() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void effectEnemy(GoodDriver source) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(double deltaTime) {
		// TODO Auto-generated method stub
		
	}

}
