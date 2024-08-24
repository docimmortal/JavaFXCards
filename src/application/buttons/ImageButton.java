package application.buttons;

import java.io.File;

import application.fxcomponents.ImageLoader;
import application.player.entities.RPGPlayer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ImageButton extends Group {

	private ImageView imageView;
	private ImageView noHoverImage;
	private ImageView hoverImage;
	protected Group myParent;
	
	public ImageButton(Group myParent, String path, String filename, int  x, int y) {
		String[] parts = filename.split("\\.");
		this.myParent=myParent;
		noHoverImage = ImageLoader.load(path+parts[0]+"."+parts[1],false);
		String workingDir = System.getProperty("user.dir");
		String hover=workingDir+path+parts[0]+"-hover."+parts[1];
		File f = new File(hover);
		if(f.exists() && !f.isDirectory()) { 
			hoverImage = ImageLoader.load(path+parts[0]+"-hover."+parts[1],false);
		} else {
			hoverImage=noHoverImage;
		}
		imageView = ImageLoader.load(path+parts[0]+"."+parts[1],false);
		imageView.setLayoutX(x);
		imageView.setLayoutY(y);
		imageView.setId(filename+Math.random());
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
	
	public ImageButton(Group myParent, String filename, int  x, int y) {
		this(myParent, "images\\buttons\\", filename, x ,y);
		imageView.setId(filename+Math.random());
	}
	
	public Group getMyParent() {
		return myParent;
	}
	
	public RPGPlayer getPlayer() {
		return (RPGPlayer)myParent.lookup("#Player");
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
