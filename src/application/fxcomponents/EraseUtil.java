package application.fxcomponents;

import application.entities.Enemy;
import application.player.entities.Player;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class EraseUtil {

	public static void eraseEnemy(Enemy enemy, Group group, int offset) {
		// hard coded to currently work with one enemy
		int no=enemy.getEnemyNumber();
		int enemyIndex=ScreenUtil.getIndexOfId(group,"#Enemy1");
		Group enemyGroup = (Group) group.getChildren().get(enemyIndex+no-1);
		enemyGroup.getChildren().set(0, ImageLoader.load("images\\enemies\\no-enemy.png", false));
		enemyGroup.getChildren().set(1, ImageLoader.load("images\\enemies\\no-enemy.png", false));
		enemyGroup.getChildren().set(2, new Text(""));
		enemyGroup.getChildren().set(3, ImageLoader.load("images\\enemies\\no-enemy.png", false));
		enemyGroup.getChildren().set(4, new Text(""));
	}
	
	public static void erase(String id, Group group) {
		System.out.print("EraseUtil erase "+id+"\n\n");
		int index=ScreenUtil.getIndexOfId(group,id);
		if (index>-1) {
			group.getChildren().set(index,ImageLoader.load("images\\enemies\\no-enemy.png", false));
		}
		System.out.println("EraseUtil END");
	}
	
	public static void redraw(Group group, Player player) {
		VBox pane = new VBox(1, new HBox(group));
		Scene scene = new Scene(pane, 1500, 900);
		player.getStage().setScene(scene);
		player.getStage().show();
	}
}
