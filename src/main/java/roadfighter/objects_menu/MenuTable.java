package roadfighter.objects_menu;

//import java.util.HashMap;

//import javafx.event.WeakEventHandler;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableColumn.CellEditEvent;
//import javafx.scene.control.TablePosition;
//import javafx.scene.control.TableView;
//import javafx.scene.control.cell.PropertyValueFactory;
//import roadfighter.client.utils.Lobby;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import roadfighter.interfaces.Renderable;
import roadfighter.utils.GameObject;

public class MenuTable extends GameObject implements Renderable {

	private HBox render;
//	private TableView<Lobby> table;
//	private HashMap<Integer, Lobby> lobbies;
	
	public MenuTable(double x, double y) {
//		lobbies = new HashMap<Integer, Lobby>();
//		table = new TableView<Lobby>();
//		TableColumn<Lobby, String> nameColumn = new TableColumn<Lobby, String>("lobby name");
//		TableColumn<Lobby, String> playerCountColumn = new TableColumn<Lobby, String>("vacancy");
//		TableColumn<Lobby, String> stateColumn = new TableColumn<Lobby, String>("playing");
//		table.getColumns().add(nameColumn);
//		table.getColumns().add(playerCountColumn);
//		table.getColumns().add(stateColumn);
//		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
//		nameColumn.setSortable(false);
//		playerCountColumn.setCellValueFactory(new PropertyValueFactory<>("currentPlayersString"));
//		playerCountColumn.setSortable(false);
//		stateColumn.setCellValueFactory(new PropertyValueFactory<>("playing"));
//		stateColumn.setSortable(false);
//		
////		table.getItems().add(new Lobby(1, "nyan", 3));
////		nameColumn.setOnEditStart((CellEditEvent<Lobby, String> event) -> {
////			TablePosition<Lobby, String> pos = event.getTablePosition();
////			
////			event.getTableView().getItems().get(pos.getRow()).setCurrentPlayers(1);
////		}); 
//		//no entiendo esta mierda y me esta volviendo loco
//		
//		render = new HBox(table);
//		render.setTranslateX(x);
//		render.setTranslateY(y);
	}
//	
//	public void addLobby(Lobby lobby) {
//		lobbies.put(lobby.getId(), lobby);
//		table.getItems().add(lobby);
//	}
//	
//	public void removeLobby(Lobby lobby) {
//		lobbies.remove(lobby.getId());
//		table.getItems().remove(lobby);
//	}
	
	@Override
	public Node getRender() {
		return render;
	}

	@Override
	public void destroy() {}
	
}
