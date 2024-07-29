package application.buttons;

import application.player.entities.DemoPlayer;
import application.player.entities.Player;
import application.screens.SplashScreen;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LeaveButton extends ImageButton {
	
	private Player player;

	public LeaveButton(String filename, int x, int y, Player player) {
		super(filename, x, y, player);
		this.player=player;
	}
	
	public void doAction() {
		SplashScreen spl = new SplashScreen("map.jpg");
		
		spl.addButton((ImageButton)((DemoPlayer)player).getStartButton());
		VBox pane =  new VBox(1, new HBox(spl.getSplashScreen()));
		
		// Final steps to render the scene
		Scene scene = new Scene(pane, 1500, 900);
		player.getStage().setScene(scene);
		player.getStage().show();
	}

}
