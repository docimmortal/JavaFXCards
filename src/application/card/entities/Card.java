package application.card.entities;

import application.fxcomponents.ImageLoader;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Card {

	private ImageView imageView;
	private boolean canUse;
	private Player player;
	
	public Card(String filename, Player player) {
		imageView = ImageLoader.load(filename,false);
		imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
	    	@Override
	        public void handle(MouseEvent event) {
	    		if (checkUsability()) {
	   	    		useTheCard();
	    		}
	    	}
		});
		this.player=player;
		canUse=true;
	}

	public final ImageView getImageView() {
		return imageView;
	}
	
	public final void setCanUse(boolean canUse) {
		this.canUse=canUse;
	}
	
	public final boolean getCanUse() {
		return canUse;
	}

	public final Player getPlayer() {
		return player;
	}
	
	/*
	 *  Methods that can be overridden
	 */
	
	public boolean checkUsability() {
		return true;
	}
	
	public void useTheCard() {
		System.out.println("Using card does nothing.");
	}


}
