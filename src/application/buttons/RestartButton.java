package application.buttons;

import application.player.entities.DemoPlayer;
import application.screens.MapScreen;
import application.utils.MapUtil;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class RestartButton extends ImageButton {

	private MapScreen map;
	
	public RestartButton(Group myParent, String filename, int x, int y) {
		super(myParent, filename, x, y);;
	}
	
	public void doAction() {
		boolean playerFound=false;
		Node node = null;
		int index=0;
		while (!playerFound) {
			node=myParent.getChildren().get(index);
			if (node.getId().equals("Player")) {
				playerFound=true;
			} else {
				index++;
			}
		}
		
		DemoPlayer dp = (DemoPlayer)node;
		// We need to create a new player
		Stage stage = dp.getStage();

		// group should only have player object.
		myParent.getChildren().clear();
		dp = new DemoPlayer(myParent,stage);
		myParent.getChildren().add(dp);
		
		map = new MapScreen();
		map.setId("#Map");
		dp.setMapScreen(map);

		MapUtil mu = new MapUtil(myParent);
		mu.setMapScreen(map);
		mu.setDefaultLocations();
		myParent.getChildren().add(mu);
		myParent.getChildren().add(map);

		// Final steps to render the scene
		Scene scene = new Scene(new VBox(myParent), 1500, 900);
		getPlayer().getStage().setScene(scene);
		getPlayer().getStage().show();
	}

}
