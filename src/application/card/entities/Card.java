package application.card.entities;

import application.fxcomponents.ImageLoader;
import application.player.entities.Player;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Card {

	private ImageView imageView;
	private String cardName;
	private boolean canUse;
	private Player player; // required to manipulate player variables.
	private int cardNo;
	private static int staticNo=1;
	
	public Card(String filename, String cardName, Player player) {
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
		this.cardName=cardName;
		canUse=true;
		cardNo=staticNo++;
	}
	
	public int getCardNo() {
		return cardNo;
	}
	
	public String getCardName() {
		return cardName;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Card [cardName=");
		builder.append(cardName);
		builder.append(", cardNo=");
		builder.append(cardNo);
		builder.append(", canUse=");
		builder.append(canUse);
		builder.append("]");
		return builder.toString();
	}


}
