package roadfighter.objects_menu;

import java.util.ArrayList;
import roadfighter.interfaces.Updatable;
import roadfighter.objects.Coordinate;
import roadfighter.objects.Player;

public class ScoreText extends GenericText implements Updatable {

	private ArrayList<Player> players;
	private ArrayList<Integer> points;
	
	public ScoreText(ArrayList<Player> players, Coordinate coordinate) {
		super(coordinate);
		this.players = players;
		points = new ArrayList<Integer>(players.size());
		for (int i = 0; i < players.size(); i++)
			points.add(0);

		String newText = "";
		int player = 1;
		for (Integer score : points) {
			newText = newText + "Player "+player+": "+score + "\n";
			player++;
		}
		this.setText(newText);
	}
	
	@Override
	public void update(double deltaTime) {
		Player player;
		boolean changeText = false;
		
		for(int i = 0; i < players.size(); i++) {
			player = players.get(i);
			if (player.hasPickedUpPoints()) {
				points.set(i, player.readPoints());
				changeText = true;
			}
		}
		
		if(changeText) {
			String newText = "";
			int numPLayer = 1;
			for (Integer score : points) {
				newText = newText + "Player "+numPLayer+": "+score + "\n";
				numPLayer++;
			}
			this.setText(newText);
		}
	}
	
}
