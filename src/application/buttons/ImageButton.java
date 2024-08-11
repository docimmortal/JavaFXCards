package application.buttons;

import application.fxcomponents.ImageLoader;
import application.player.entities.DemoPlayer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ImageButton extends Group {

	private ImageView imageView;
	private ImageView noHoverImage;
	private ImageView hoverImage;
	protected Group myParent;
	
	public ImageButton(Group myParent, String filename, int  x, int y) {
		String[] parts = filename.split("\\.");
		this.myParent=myParent;
		noHoverImage = ImageLoader.load("images\\buttons\\"+parts[0]+"."+parts[1],false);
		hoverImage = ImageLoader.load("images\\buttons\\"+parts[0]+"-hover."+parts[1],false);
		imageView = ImageLoader.load("images\\buttons\\"+parts[0]+"."+parts[1],false);
		imageView.setLayoutX(x);
		imageView.setLayoutY(y);
		imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
	    	@Override
	        public void handle(MouseEvent event) {
	    		doAction();
	    	}
		});
		imageView.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
			@Override
	        public void handle(MouseEvent event) {
				imageView.setImage(hoverImage.getImage());
			}
		});
		imageView.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
			@Override
	        public void handle(MouseEvent event) {
				imageView.setImage(noHoverImage.getImage());
			}
		});
	}
	
	public Group getMyParent() {
		return myParent;
	}
	
	public DemoPlayer getPlayer() {
		return (DemoPlayer)myParent.lookup("#Player");
	}

	public final ImageView getImageView() {
		return imageView;
	}
	
	/*
	 * Methods that can be overridden
	 */
	public void doAction() {
		
	}

}
