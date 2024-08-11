package application.buttons;

import application.fxcomponents.ScreenUtil;
import application.player.entities.DemoPlayer;
import application.screens.SplashScreen;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class LeaveButton extends ImageButton {

	public LeaveButton(Group myParent, String filename, int x, int y) {
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
				myParent.getChildren().clear();
				myParent.getChildren().add(node);
			} else {
				index++;
			}
		}
		
		SplashScreen spl = new SplashScreen("map.jpg");
		
		RestartButton startButton = new RestartButton(myParent,"Button-start.jpg",1200,700);	
		spl.addButton(startButton);
		myParent.getChildren().add(startButton);
		myParent.getChildren().add(spl);
		
		index=ScreenUtil.getIndexOfId(myParent, "#Player");
		((DemoPlayer)myParent.getChildren().get(index)).setNoInitialHandSet();
		
		// Final steps to render the scene
		Scene scene = new Scene(new VBox(getMyParent()), 1500, 900);
		getPlayer().getStage().setScene(scene);
		getPlayer().getStage().show();
	}

}
