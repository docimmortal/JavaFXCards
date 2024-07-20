package application.card.entities;

import application.fxcomponents.ImageLoader;
import javafx.scene.image.ImageView;

public class Entity {

	private ImageView imageView;
	private Player player;
	
	public Entity(String filename, Player player, int x, int y) {
		imageView = ImageLoader.load(filename,false);
		imageView.setLayoutX(x);
		imageView.setLayoutY(y);
		this.player=player;
	}
	
	public final ImageView getImageView() {
		return imageView;
	}
	

	public final Player getPlayer() {
		return player;
	}

}
