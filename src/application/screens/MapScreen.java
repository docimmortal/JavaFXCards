package application.screens;

import application.buttons.MapLocation;
import application.fxcomponents.ImageLoader;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MapScreen  extends Group{

	public MapScreen() {
		ImageView imageView = ImageLoader.load("images\\backgrounds\\map.jpg", false);
		this.getChildren().add(imageView);
		setId("MapScreen");
	}
	
	public void addMapLocation(MapLocation button) {
		this.getChildren().add(button.getImageView());
	}
	
	public VBox getScreen() {
		// Final steps to render the scene
		VBox pane = new VBox(1, new HBox(this));
		return pane;
	}
}
