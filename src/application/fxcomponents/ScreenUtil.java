package application.fxcomponents;

import application.player.entities.Player;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ScreenUtil {

	public static ImageView GameOver() {
		ImageView imageView = ImageLoader.load("images\\backgrounds\\GameOver.png",false);
		//int x=(int)Main.screenBounds.getWidth()/2;
		//int y=(int)Main.screenBounds.getHeight()/2;
		
		// temp
		int x=750-(int)(imageView.getImage().getWidth()/2);
		int y=450-(int)(imageView.getImage().getHeight()/2);
		
		imageView.setLayoutX(x);
		imageView.setLayoutY(y);
		imageView.setId("GameOver");
		return imageView;
	}
	
	public static Node getNodeOfId(Group myParent, String id) {
		return myParent.lookup(id);
	}
	
	public static int getIndexOfId(Group myParent, String id) {
		Node node = myParent.lookup(id);
		int index=-1;
		if (node != null) {
			index=myParent.getChildren().indexOf(node);
		}
		return index;
	}
	
	public static void redraw(Group group, Player player) {
		VBox pane = new VBox(1, new HBox(group));
		Scene scene = new Scene(pane, 1500, 900);
		player.getStage().setScene(scene);
		player.getStage().show();
	}
}
