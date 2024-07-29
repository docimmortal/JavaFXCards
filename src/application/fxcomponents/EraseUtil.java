package application.fxcomponents;

import application.player.entities.Player;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class EraseUtil {

	public static void eraseEnemy(Group group, int offset) {
		group.getChildren().set(6+offset*5, ImageLoader.load("images\\enemies\\no-enemy.png", false));
		group.getChildren().set(7+offset*5, ImageLoader.load("images\\enemies\\no-enemy.png", false));
		group.getChildren().set(8+offset*5, new Text(""));
		group.getChildren().set(9+offset*5, ImageLoader.load("images\\enemies\\no-enemy.png", false));
		group.getChildren().set(10+offset*5, new Text(""));
	}
	
	public static void eraseDiscard(int index, Group group) {
		group.getChildren().set(index,ImageLoader.load("images\\enemies\\no-enemy.png", false));
	}
	
	public static void redraw(Group group, Player player) {
		VBox pane = new VBox(1, new HBox(group));
		Scene scene = new Scene(pane, 1500, 900);
		player.getStage().setScene(scene);
		player.getStage().show();
	}
}
