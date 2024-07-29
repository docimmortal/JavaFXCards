package application.fxcomponents;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ImageLoader {

	// Loads and image and stores it in an ImageView object (used by JavaFX)
	// If external=false, the image is in this project under images.
	// If external=true, the image is stored somewhere else on the hard drive.
	public static ImageView load(String filename, String itemName, boolean external,  List<HBox> panes, HBox hboxAction, Stage stage) {
	    return load(filename, itemName, external, panes, hboxAction, stage, 0, 0);
	}
	
	public static ImageView load(String filename, String itemName, boolean external,  List<HBox> panes, HBox hboxAction, Stage stage,int x, int y) {
	    ImageView imageView = new ImageView();
	    Image image = null;
	    if (external) {
	    	image=getImageFromFileSystem(filename,x,y);
	    } else {
	    	image=getProjectImage(filename,x,y);
	    }
	    if (itemName!=null && itemName.length()>0) {
	    	// clickable item on screen
		    imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		    	@Override
		        public void handle(MouseEvent event) {
		    		HBox[] array = panes.toArray(new HBox[panes.size()]);
		    		VBox pane = new VBox(2, array);
					Scene scene = new Scene(pane, 1000, 700);
					stage.setScene(scene);
		            event.consume();
		        }
		    });
	    }
	    imageView.setImage(image);
	    imageView.setX(0);
	    imageView.setY(0);
	    imageView.setPreserveRatio(true);
		return imageView;
	}

	// Loads and image and stores it in an ImageView object (used by JavaFX)
	// If external=false, the image is in this project under images.
	// If external=true, the image is stored somewhere else on the hard drive.
	public static ImageView load(String filename, boolean external) {
		return load(filename, external, 0, 0);
	}
	
	public static ImageView load(String filename, boolean external, int x, int y) {
		ImageView imageView = new ImageView();
		if (filename != null && filename.length()>0) {
			Image image = null;
			if (external) {
				image=getImageFromFileSystem(filename,x,y);
			} else {
				image=getProjectImage(filename,x,y);
			}
			imageView.setImage(image);
			imageView.setPreserveRatio(true);
		}
		return imageView;
	}
	
	private static Image getProjectImage(String filename, int x, int y) {
		String workingDir = System.getProperty("user.dir");
		return getImageFromFileSystem(workingDir+"\\"+filename,x,y);
	}
	
	private static Image getImageFromFileSystem(String filename,int x, int y) {
		Image image = null;
		try (InputStream stream = new FileInputStream(filename)) {
			image = new Image(stream,x,y,false,false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}
}
