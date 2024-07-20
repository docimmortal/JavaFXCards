package application.utils;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class TextUtil {

	public static Text initText(String textMsg, int x, int y) {
		return initText(textMsg, x, y, Color.WHITE);
	}
	
	public static Text initText(String textMsg, int x, int y, Color color) {
		Text text = new Text(textMsg);
		text.setLayoutX(x);
		text.setLayoutY(y);
		text.setFont(new Font(20));
		text.setFill(color);
		text.setStyle("-fx-font-weight: bold");
		return text;
	}
}
