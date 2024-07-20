package application.card.entities;

import application.fxcomponents.ImageLoader;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ImageButton {

	private ImageView imageView;
	private ImageView noHoverImage;
	private ImageView hoverImage;
	private Player player;
	
	public ImageButton(String filename, int  x, int y, Player player) {
		noHoverImage = ImageLoader.load("images\\buttons\\"+filename+".jpg",false);
		hoverImage = ImageLoader.load("images\\buttons\\"+filename+"-hover.jpg",false);
		imageView = ImageLoader.load("images\\buttons\\"+filename+".jpg",false);
		imageView.setLayoutX(x);
		imageView.setLayoutY(y);
		imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
	    	@Override
	        public void handle(MouseEvent event) {
	    		System.out.println("CLICKED!");
	    		doAction();
	    	}
		});
		imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
	        public void handle(MouseEvent event) {
				System.out.println("NoHover entered!");
				imageView.setImage(hoverImage.getImage());
			}
		});
		imageView.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
	        public void handle(MouseEvent event) {
				System.out.println("Hover exited!");
				imageView.setImage(noHoverImage.getImage());
			}
		});
		this.player=player;
	}

	public final ImageView getImageView() {
		return imageView;
	}
	
	public final Player getPlayer() {
		return player;
	}
	
	/*
	 * Methods that can be overridden
	 */
	public void doAction() {
		
	}

}
