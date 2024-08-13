package application.fxcomponents;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

public class ScreenUtil {

	public static ImageView GameOver() {
		ImageView imageView = ImageLoader.load("images\\backgrounds\\GameOver.png",false);
		//int x=(int)Main.screenBounds.getWidth()/2;
		//int y=(int)Main.screenBounds.getHeight()/2;
		
		// temp
		int x=750-(int)(imageView.getImage().getWidth()/2);
		int y=450-(int)(imageView.getImage().getHeight()/2);
		
		imageView.setLayoutX(x);
		imageView.setLayoutY(y);
		imageView.setId("GameOver");
		return imageView;
	}
	
	public static Node getNodeOfId(Group myParent, String id) {
		return myParent.lookup(id);
	}
	
	public static int getIndexOfId(Group myParent, String id) {
		Node node = myParent.lookup(id);
		int index=-1;
		if (node == null) {
			System.out.println("*********ERROR ScreenUtil getIndexOfId: "+id+" not found");
		} else {
			index=myParent.getChildren().indexOf(node);
		}
		return index;
	}

}
