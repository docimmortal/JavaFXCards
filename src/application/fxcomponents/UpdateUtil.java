package application.fxcomponents;

import javafx.scene.Group;
import javafx.scene.text.Text;

public class UpdateUtil {

	public static void updateGroupText(Group myParent, String id, Text text) {
		int index=ScreenUtil.getIndexOfId(myParent, id);
		myParent.getChildren().set(index, text);
	}
	
}
