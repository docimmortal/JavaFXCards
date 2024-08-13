package application.buttons;

import application.fxcomponents.ScreenUtil;
import application.player.entities.DemoPlayer;
import application.screens.MapScreen;
import application.utils.MapUtil;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class LeaveButton extends ImageButton {

	public LeaveButton(Group myParent, String filename, int x, int y) {
		super(myParent, filename, x, y);;
	}
	
	public void doAction() {
		DemoPlayer player=null;
		MapUtil mapUtil=null;
		MapScreen map=null;
		int index=0;
		while (player==null||map==null) {
			Node node=myParent.getChildren().get(index);
			if (node.getId().equals("Player")) {
				player=(DemoPlayer)node;
			} else if (node.getId().equals("MapUtil")) {
				mapUtil=(MapUtil)node;
				map=mapUtil.getMapScreen();
			}
			index++;
		}
		
		myParent.getChildren().clear();
		myParent.getChildren().add(player);
		myParent.getChildren().add(mapUtil);
		
		index=ScreenUtil.getIndexOfId(myParent, "#Player");
		player.setNoInitialHandSet();
		
		// Final steps to render the scene
		Scene scene = new Scene(new VBox(map), 1500, 900);
		player.getStage().setScene(scene);
		player.getStage().show();
	}

}
