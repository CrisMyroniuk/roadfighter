package roadfighter.objects_menu;

import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import roadfighter.interfaces.Renderable;
import roadfighter.interfaces.Updatable;
import roadfighter.objects.Player;
import roadfighter.utils.GameObject;
import roadfighter.utils.GameObjectBuilder;

public class Lifes extends GameObject implements Renderable,Updatable {

	private ImageView render;
	private Image sprite;
	private double x;
	private double y;
	private Player player;
	private int numVida;

	public Lifes(String image,int x,int y,Player player,int numVida) {
		this.x = x;
		this.y = y;
		this.player = player;
		this.numVida = numVida;
		initImages(image);
	}
	
	private void initImages(String image) {
		sprite = new Image(image, 40, 40, true, true);
		render = new ImageView(sprite);
		render.setTranslateX(x);
		render.setTranslateY(y);
	}
	
	
	@Override
	public Node getRender() {
		return render;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(double deltaTime) {
		Player player;
		
			player = this.player;
			int cantVi = player.getCantLifes();
			if (player.getCantLifes() < 3) {
				if(cantVi == 2 && numVida == 3) {
					GameObjectBuilder builder = GameObjectBuilder.getInstance();
					builder.remove(this);
				}else if(cantVi == 1 && numVida == 2) {
					GameObjectBuilder builder = GameObjectBuilder.getInstance();
					builder.remove(this);
				} else if(cantVi == 0 && numVida == 1){
					GameObjectBuilder builder = GameObjectBuilder.getInstance();
					builder.remove(this);
				}
				
			}
		
		
	}
	
}
