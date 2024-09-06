package application.buttons;

import application.fxcomponents.EraseUtil;
import application.fxcomponents.ScreenUtil;
import application.player.entities.RPGPlayer;
import application.screens.MapScreen;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class LeaveButton extends ImageButton {

	public LeaveButton(Group myParent, String filename, int x, int y) {
		super(myParent, filename, x, y);;
	}
	
	public void doAction() {
		MapScreen map=EraseUtil.clearAllButRequired(myParent);
		RPGPlayer player = (RPGPlayer)ScreenUtil.getNodeOfId(myParent, "#Player");
		
		// Final steps to render the scene
		Scene scene = new Scene(new VBox(map), 1500, 900);
		player.getStage().setScene(scene);
		player.getStage().show();
	}

}
