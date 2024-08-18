package application.buttons;

import application.player.entities.DemoPlayer;
import application.screens.MapScreen;
import application.utils.EndTurnUtil;
import application.utils.MapUtil;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import application.card.effects.StatType;
import application.entities.Character;

public class RestartButton extends ImageButton {

	private MapScreen map;
	
	public RestartButton(Group myParent, String filename, int x, int y) {
		super(myParent, filename, x, y);;
	}
	
	public void doAction() {
		Node node = null;
		DemoPlayer demo = null;
		EndTurnUtil endTurn=null;
		int index=0;
		while (demo==null || endTurn==null) {
			node=myParent.getChildren().get(index);
			if (node.getId().equals("Player")) {
				demo=(DemoPlayer)node;
			} else if (node.getId().equals("EndTurnUtil")) {
				endTurn=(EndTurnUtil)node;
			}
			index++;
		}
		
		// We need to create a new player
		Stage stage = demo.getStage();
		Character character = demo.getCharacter();
		character.set(StatType.HEALTH, character.get(StatType.MAX_HEALTH));

		// group should only have player and EndUtil objects.
		myParent.getChildren().clear();
		demo = new DemoPlayer(myParent,stage);
		demo.setCharacter(character);
		myParent.getChildren().add(demo);
		myParent.getChildren().add(endTurn);
		
		map = new MapScreen();
		map.setId("#Map");
		demo.setMapScreen(map);

		MapUtil mu = new MapUtil(myParent);
		mu.setMapScreen(map);
		mu.setDefaultLocations();
		myParent.getChildren().add(mu);

		// Final steps to render the scene
		Scene scene = new Scene(new VBox(myParent), 1500, 900);
		getPlayer().getStage().setScene(scene);
		getPlayer().getStage().show();
	}

}
