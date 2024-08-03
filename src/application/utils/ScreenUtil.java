package application.utils;

import application.fxcomponents.ImageLoader;
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
		return imageView;
	}
}
