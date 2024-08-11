package application.card.entities;

import application.fxcomponents.ImageLoader;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Card extends Group {

	private ImageView imageView;
	private String cardName;
	protected Group myParent;
	
	public Card(Group myParent, String filename, String cardName) {
		if (! filename.contains("no-card")) {
				imageView = ImageLoader.load(filename,false,169,244);
		} else {
			imageView = ImageLoader.load(filename,false);
		}
		imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
	    	@Override
	        public void handle(MouseEvent event) {
	    		if (checkUsability()) {
	   	    		useTheCard();
	    		}
	    	}
		});
		this.cardName=cardName;
		this.myParent=myParent;
	}
	
	public String getCardName() {
		return cardName;
	}
	

	public final ImageView getImageView() {
		return imageView;
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
		builder.append("]");
		return builder.toString();
	}


}
