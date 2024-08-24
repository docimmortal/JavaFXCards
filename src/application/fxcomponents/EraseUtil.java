package application.fxcomponents;

import application.entities.Enemy;
import application.player.entities.Player;
import application.player.entities.RPGPlayer;
import application.screens.MapScreen;
import application.utils.EndTurnUtil;
import application.utils.MapUtil;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class EraseUtil {

	public static void eraseEnemy(Enemy enemy) {
		// hard coded to currently work with one enemy
		enemy.getChildren().set(0, ImageLoader.load("images\\enemies\\no-enemy.png", false));
		enemy.getChildren().set(1, ImageLoader.load("images\\enemies\\no-enemy.png", false));
		enemy.getChildren().set(2, new Text(""));
		enemy.getChildren().set(3, ImageLoader.load("images\\enemies\\no-enemy.png", false));
		enemy.getChildren().set(4, new Text(""));
	}
	
	public static void erase(String id, Group group) {
		Node node = group.lookup(id);
		group.getChildren().remove(node);
	}

	public static MapScreen clearAllButRequired(Group myParent) {
		// Objects we want to keep
		RPGPlayer player=null;
		MapUtil mapUtil=null;
		EndTurnUtil endTurn=null;
		
		// returned value
		MapScreen map=null;
		
		int index=0;
		while (player==null||map==null||endTurn==null) {
			Node node=myParent.getChildren().get(index);
			if (node.getId().equals("Player")) {
				player=(RPGPlayer)node;
			} else if (node.getId().equals("MapUtil")) {
				mapUtil=(MapUtil)node;
				map=mapUtil.getMapScreen();
			} else if (node.getId().equals("EndTurnUtil")) {
				endTurn=(EndTurnUtil)node;
			}
			index++;
		}
		
		myParent.getChildren().clear();
		myParent.getChildren().add(player);
		myParent.getChildren().add(mapUtil);
		myParent.getChildren().add(endTurn);
		
		player.setNoInitialHandSet();
		
		return map;
	}
}
