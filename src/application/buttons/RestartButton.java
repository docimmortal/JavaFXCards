package application.buttons;

import application.player.entities.RPGPlayer;
import application.screens.MapScreen;
import application.utils.MapUtil;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import application.card.effects.StatType;
import application.entities.Character;
import application.fxcomponents.EraseUtil;
import application.fxcomponents.ScreenUtil;

public class RestartButton extends ImageButton {

	private MapScreen map;
	
	public RestartButton(Group myParent, String filename, int x, int y) {
		super(myParent, filename, x, y);;
	}
	
	public void doAction() {
		/*
		Node node = null;
		RPGPlayer demo = null;
		EndTurnUtil endTurn=null;
		int index=0;
		while (demo==null || endTurn==null) {
			node=myParent.getChildren().get(index);
			if (node.getId().equals("Player")) {
				demo=(RPGPlayer)node;
			} else if (node.getId().equals("EndTurnUtil")) {
				endTurn=(EndTurnUtil)node;
			}
			index++;
		}
		// group should only have player and EndUtil objects.
		myParent.getChildren().clear();
		myParent.getChildren().add(demo);
		myParent.getChildren().add(endTurn);*/
		EraseUtil.clearAllButRequired(myParent);
		RPGPlayer player = (RPGPlayer)ScreenUtil.getNodeOfId(myParent, "#Player");
		
		// We need to create a new player
		Stage stage = player.getStage();
		Character character = player.getCharacter();
		character.set(StatType.HEALTH, character.get(StatType.MAX_HEALTH));

		player = new RPGPlayer(myParent,stage);
		player.setCharacter(character);

		map = new MapScreen();
		map.setId("#Map");
		player.setMapScreen(map);

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
