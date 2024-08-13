package application.fxcomponents;

import application.entities.Enemy;
import application.player.entities.Player;
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
		if (node==null) {
			System.out.println("************* ERROR: EraseUtil erase cannot find "+id);
		}
		group.getChildren().remove(node);
	}
	
	public static void redraw(Group group, Player player) {
		VBox pane = new VBox(1, new HBox(group));
		Scene scene = new Scene(pane, 1500, 900);
		player.getStage().setScene(scene);
		player.getStage().show();
	}
}
