package application.screens;

import application.buttons.ImageButton;
import application.fxcomponents.ImageLoader;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SplashScreen {

	private ImageView imageView;
	private Group group = new Group();
	
	public SplashScreen(String filename) {
		group = new Group();
		imageView = ImageLoader.load("images\\backgrounds\\"+filename, false);
		group.getChildren().add(imageView);
	}
	
	public void addButton(ImageButton button) {
		group.getChildren().add(button.getImageView());
	}
	
	public VBox getSplashScreen() {
		// Final steps to render the scene
		VBox pane = new VBox(1, new HBox(group));
		return pane;
	}
}
