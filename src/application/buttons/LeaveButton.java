package application.buttons;

import application.player.entities.Player;
import application.screens.SplashScreen;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LeaveButton extends ImageButton {
	
	private Player player;
	private Group group;
	private Stage stage;

	public LeaveButton(String filename, int x, int y, Player player, Group group, Stage stage) {
		super(filename, x, y, player);
		this.player=player;
		this.group=group;
		this.stage=stage;
	}
	
	public void doAction() {
		System.out.println("==============>LEAVING....");
		SplashScreen spl = new SplashScreen("map.jpg");
		
		spl.addButton(new StartButton("Button-start.jpg",1200,700, player, group, stage));
		VBox pane =  new VBox(1, new HBox(spl.getSplashScreen()));
		
		// Final steps to render the scene
		Scene scene = new Scene(pane, 1500, 900);
		stage.setScene(scene);
		stage.show();
	}

}
