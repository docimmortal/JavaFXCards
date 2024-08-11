package application.screens;

import application.buttons.ImageButton;
import application.fxcomponents.ImageLoader;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SplashScreen extends Group{

	private ImageView imageView;

	public SplashScreen(String filename) {
		imageView = ImageLoader.load("images\\backgrounds\\"+filename, false);
		this.getChildren().add(imageView);
		setId("SplashScreen");
	}
	
	public void addButton(ImageButton button) {
		this.getChildren().add(button.getImageView());
	}
	
	public VBox getSplashScreen() {
		// Final steps to render the scene
		VBox pane = new VBox(1, new HBox(this));
		return pane;
	}
}
