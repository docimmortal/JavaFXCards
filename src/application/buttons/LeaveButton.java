package application.buttons;

import application.player.entities.Player;
import application.screens.SplashScreen;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LeaveButton extends ImageButton {
	
	private Player player;
	private Group group;

	public LeaveButton(String filename, int x, int y, Player player, Group group) {
		super(filename, x, y, player);
		this.player=player;
		this.group=group;
	}
	
	public void doAction() {
		System.out.println("==============>LEAVING....");
		SplashScreen spl = new SplashScreen("map.jpg");
		
		spl.addButton(new StartButton("Button-start.jpg",1200,700, player, group));
		VBox pane =  new VBox(1, new HBox(spl.getSplashScreen()));
		
		// Final steps to render the scene
		Scene scene = new Scene(pane, 1500, 900);
		player.getStage().setScene(scene);
		player.getStage().show();
	}

}
