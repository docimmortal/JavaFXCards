package application.screens;

import application.buttons.ImageButton;
import application.fxcomponents.ImageLoader;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SplashScreen {

	private ImageView imageView;
	private Group splashScreenGroup = new Group();
	
	public SplashScreen(String filename) {
		splashScreenGroup = new Group();
		imageView = ImageLoader.load("images\\backgrounds\\"+filename, false);
		splashScreenGroup.getChildren().add(imageView);
	}
	
	public void addButton(ImageButton button) {
		splashScreenGroup.getChildren().add(button.getImageView());
	}
	
	public VBox getSplashScreen() {
		// Final steps to render the scene
		VBox pane = new VBox(1, new HBox(splashScreenGroup));
		return pane;
	}
}
