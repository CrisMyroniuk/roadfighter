package roadfighter.interfaces;

import javafx.scene.shape.Shape;
import roadfighter.objects.CarPlayer;

// TODO separar que pueda colisionar y ser colisionado o solo ser colisionado
public interface Collideable {
	public Shape getCollider();
	public void effectPlayer(CarPlayer source); // le agregue esto para no tener que hacer getClass() cuando colisionan
}
