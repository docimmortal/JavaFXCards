package application.buttons;

import application.fxcomponents.EraseUtil;
import application.fxcomponents.ScreenUtil;
import application.player.entities.DemoPlayer;
import application.screens.MapScreen;
import application.utils.MapUtil;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class StartButton extends ImageButton {

	private MapScreen map;
	
	public StartButton(Group myParent, String filename, int x, int y) {
		super(myParent, filename, x, y);
		setId("StartButton");
	}
	
	public DemoPlayer getPlayer() {
		return (DemoPlayer)getMyParent().lookup("#Player");
	}

	@Override
	public void doAction(){
		DemoPlayer dp = getPlayer();
		Group myParent=getMyParent();
		EraseUtil.erase("#StartButton", myParent);
		EraseUtil.erase("#SplashScreen", myParent);
		initScreen(myParent, dp);
	}
	
	private void initScreen(Group group, DemoPlayer player) {
		map = new MapScreen();
		MapUtil mu = (MapUtil)ScreenUtil.getNodeOfId(myParent, "#MapUtil");
		mu.setMapScreen(map);
		mu.setDefaultLocations();
		player.setMapScreen(map);
		
		VBox pane = new VBox(1, new HBox(map));
		Scene scene = new Scene(pane, 1500, 900);
		player.getStage().setScene(scene);
		player.getStage().show();
	}
	
	public void debugGroup() {
		int size=getMyParent().getChildren().size();
		for (int i=0; i<size;i++) {
			System.out.println("==>"+getMyParent().getChildren().get(i).getId());
		}
	}

}
