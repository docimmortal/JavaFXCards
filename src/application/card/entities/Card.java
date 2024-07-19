package application.card.entities;

import application.fxcomponents.ImageLoader;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Card {

	private ImageView imageView;
	private boolean used;
	private boolean canUse;
	private Player player;
	
	public Card(String filename, Player player) {
		imageView = ImageLoader.load(filename,false);
		imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
	    	@Override
	        public void handle(MouseEvent event) {
	    		if (checkUsability()) {
	   	    		used=true;
	   	    		useTheCard();
	   	    		if (updateVisibilityCheck()) {
	   	    			imageView.setVisible(!used);
	   	    		}
	    		}
	    	}
		});
		this.player=player;
		canUse=true;
	}

	public ImageView getImageView() {
		return imageView;
	}
	
	public boolean isUsed() {
		return used;
	}
	
	public void setUsed(boolean used) {
		this.used=used;
		imageView.setVisible(!used);
	}
	
	public void setCanUse(boolean canUse) {
		this.canUse=canUse;
	}
	
	public boolean getCanUse() {
		return canUse;
	}
	
	public boolean checkUsability() {
		return true;
	}
	
	public void useTheCard() {
		System.out.println("Using card does nothing.");
	}
	
	public boolean updateVisibilityCheck() {
		return true;
	}
	
	// to manually hide card somehow
	public void hideCardImage() {
		imageView.setVisible(!used);
	}

	public Player getPlayer() {
		return player;
	}
	
}
